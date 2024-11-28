CREATE TABLE Abonnement (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    numCarte BIGINT UNIQUE NOT NULL,
    solde DOUBLE NOT NULL,
    utilisateur_id BIGINT,
    FOREIGN KEY (utilisateur_id) REFERENCES UtilisateurAbonne(id) ON DELETE CASCADE
);

CREATE TABLE CarteAbonnement (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    solde DOUBLE NOT NULL,
    controlParental BOOLEAN NOT NULL,
    utilisateur_id BIGINT,
    FOREIGN KEY (utilisateur_id) REFERENCES UtilisateurAbonne(id) ON DELETE CASCADE
);

CREATE TABLE Film (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titre VARCHAR(255) NOT NULL,
    genre VARCHAR(255),
    acteur VARCHAR(255),
    rating DOUBLE
);

CREATE TABLE Historique (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    dateAction DATETIME NOT NULL,
    utilisateur_id BIGINT,
    FOREIGN KEY (utilisateur_id) REFERENCES UtilisateurAbonne(id) ON DELETE CASCADE
);

CREATE TABLE Location (
    idLoc BIGINT AUTO_INCREMENT PRIMARY KEY,
    dateLocation DATETIME NOT NULL,
    tarif DOUBLE NOT NULL,
    utilisateur_id BIGINT,
    FOREIGN KEY (utilisateur_id) REFERENCES UtilisateurAbonne(id) ON DELETE CASCADE
);

CREATE TABLE QRCode (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    contenu VARCHAR(255) NOT NULL,
    validite BOOLEAN NOT NULL
);

CREATE TABLE RetourBluray (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    dateRetour DATETIME NOT NULL,
    bluray_id BIGINT,
    FOREIGN KEY (bluray_id) REFERENCES Film(id) ON DELETE CASCADE
);

CREATE TABLE UtilisateurAbonne (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    adresse VARCHAR(255),
    email VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE UtilisateurNonAbonne (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    carteBancaire VARCHAR(255)
);