-- T1 - Trigger pour vérifier et empêcher une location si le solde de l’abonnement est inférieur ou égal à 15 euros :

-- Supprimer le trigger s'il existe déjà
DROP TRIGGER IF EXISTS VerifierSoldeAbonnement;

DELIMITER $$

CREATE TRIGGER VerifierSoldeAbonnement
BEFORE INSERT ON Location
FOR EACH ROW
BEGIN
    DECLARE soldeUtilisateur DOUBLE;

    -- Vérifier si l'utilisateur est abonné
    IF EXISTS (SELECT 1 FROM UtilisateurAbonne WHERE id = NEW.utilisateur_id) THEN
        -- Récupérer le solde de l'utilisateur abonné
        SELECT solde INTO soldeUtilisateur
        FROM Abonnement
        WHERE utilisateur_id = NEW.utilisateur_id;

        -- Vérifier si le solde est inférieur ou égal à 15
        IF soldeUtilisateur <= 15 THEN
            -- Empêcher l'insertion (location) si le solde est insuffisant
            SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Solde insuffisant pour effectuer cette location. Veuillez recharger votre compte abonnement.';
        END IF;
    END IF;
END$$

DELIMITER ;



-- T2 - Trigger pour limiter le nombre de films qu’un utilisateur peut louer par semaine
-- Ce trigger est déclenché à chaque location et vérifie si un utilisateur a dépassé le seuil de 10 films par semaine.

-- Supprimer le trigger s'il existe déjà
DROP TRIGGER IF EXISTS LimiteFilmsParSemaine;

DELIMITER $$

CREATE TRIGGER LimiteFilmsParSemaine
BEFORE INSERT ON Location
FOR EACH ROW
BEGIN
    DECLARE nbFilmsParSemaine INT;

    -- Comptage des films loués par l'abonné cette semaine (en utilisant YEARWEEK pour déterminer la semaine)
    SELECT COUNT(*) INTO nbFilmsParSemaine
    FROM Location
    WHERE utilisateur_id = NEW.utilisateur_id 
    AND YEARWEEK(dateLocation, 1) = YEARWEEK(CURDATE(), 1); -- 1 = mode ISO, commence la semaine le lundi

    -- Vérification si l'utilisateur a atteint la limite de films par semaine (ici 10 films)
    IF nbFilmsParSemaine >= 10 THEN
        -- Si la limite est atteinte, on déclenche une exception pour empêcher l'insertion de la nouvelle location
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Limite de films par semaine atteinte.';
    END IF;
END $$

DELIMITER ;

