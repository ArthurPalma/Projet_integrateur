-- T1 - Trigger pour empêcher une location si le solde de l’abonnement est inférieur à 15 euros :

DELIMITER $$

CREATE TRIGGER VerifierSoldeAbonnement
BEFORE INSERT ON Location
FOR EACH ROW
BEGIN
    DECLARE soldeUtilisateur DOUBLE;
    
    -- Récupérer le solde de l'utilisateur à partir de son abonnement
    SELECT solde INTO soldeUtilisateur
    FROM Abonnement
    WHERE utilisateur_id = NEW.utilisateur_id;
    
    -- Vérifier si le solde est inférieur à 15
    IF soldeUtilisateur < 15 THEN
        -- Empêcher l'insertion (location) si le solde est insuffisant
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Solde insuffisant pour effectuer une location. Veuillez recharger votre abonnement.';
    END IF;
END$$

DELIMITER ;


-- T2 - Trigger pour limiter le nombre de films un utilisateur peut louer simultanément : 3 pour les abonnés et 1 pour les non-abonnés

DELIMITER $$

CREATE TRIGGER LimiteFilmsLocation
BEFORE INSERT ON Location
FOR EACH ROW
BEGIN
    DECLARE nbFilmsLocates INT;

    -- Vérification du nombre de films loués par l'utilisateur
    SELECT COUNT(*) INTO nbFilmsLocates
    FROM Location
    WHERE utilisateur_id = NEW.utilisateur_id AND YEARWEEK(dateLocation, 1) = YEARWEEK(CURDATE(), 1);

    -- Vérification si l'utilisateur est abonné ou non, et limiter le nombre de films
    IF (SELECT COUNT(*) FROM UtilisateurAbonne WHERE id = NEW.utilisateur_id) > 0 THEN
        IF nbFilmsLocates >= 3 THEN
            SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Limite de location atteinte pour un abonné.';
        END IF;
    ELSE
        IF nbFilmsLocates >= 1 THEN
            SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Limite de location atteinte pour un utilisateur non abonné.';
        END IF;
    END IF;
END $$

DELIMITER ;



-- T3 - Trigger pour appliquer une remise si l’abonné a loué plus de 20 films dans le mois
-- Ce trigger sera exécuté après chaque location. Il vérifie si un abonné a loué plus de 20 films dans le mois en cours, et lui attribue une remise.

DELIMITER $$

CREATE TRIGGER RemiseSurLocation
AFTER INSERT ON Location
FOR EACH ROW
BEGIN
    DECLARE nbFilmsLocates INT;

    -- Comptage des films loués par l'abonné ce mois-ci
    SELECT COUNT(*) INTO nbFilmsLocates
    FROM Location
    WHERE utilisateur_id = NEW.utilisateur_id AND YEAR(dateLocation) = YEAR(CURDATE()) AND MONTH(dateLocation) = MONTH(CURDATE());

    -- Appliquer une remise si plus de 20 films sont loués
    IF nbFilmsLocates > 20 THEN
        -- Remise à appliquer
        UPDATE CarteAbonnement
        SET solde = solde + 5  -- exemple d'ajout de remise de 5 €
        WHERE utilisateur_id = NEW.utilisateur_id;
    END IF;
END $$

DELIMITER ;


-- T4 - Trigger pour limiter le nombre de films qu’un utilisateur peut louer par semaine
-- Ce trigger est déclenché à chaque location et vérifie si un utilisateur a dépassé le seuil de 10 films par semaine.

DELIMITER $$

CREATE TRIGGER LimiteFilmsParSemaine
BEFORE INSERT ON Location
FOR EACH ROW
BEGIN
    DECLARE nbFilmsParSemaine INT;

    -- Comptage des films loués par l'abonné cette semaine
    SELECT COUNT(*) INTO nbFilmsParSemaine
    FROM Location
    WHERE utilisateur_id = NEW.utilisateur_id AND YEARWEEK(dateLocation, 1) = YEARWEEK(CURDATE(), 1);

    -- Vérification si l'utilisateur a atteint la limite de films par semaine
    IF nbFilmsParSemaine >= 10 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Limite de films par semaine atteinte.';
    END IF;
END $$

DELIMITER ;

