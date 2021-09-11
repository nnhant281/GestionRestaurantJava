-- phpMyAdmin SQL Dump
-- version 4.9.7
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Generation Time: Sep 11, 2021 at 04:09 PM
-- Server version: 5.7.32
-- PHP Version: 7.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `gestion_restaurant`
--

-- --------------------------------------------------------

--
-- Table structure for table `categorie`
--

CREATE TABLE `categorie` (
  `Libelle_categorie` varchar(100) NOT NULL,
  `statut` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `categorie`
--

INSERT INTO `categorie` (`Libelle_categorie`, `statut`) VALUES
('apérétif', 0),
('Boisson', 1),
('Cat', 0),
('Dessert', 1),
('Entree', 1),
('f', 0),
('Plat', 1),
('Salade', 1);

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

CREATE TABLE `client` (
  `ID_client` int(11) NOT NULL,
  `nom` varchar(150) NOT NULL,
  `prenom` varchar(150) NOT NULL,
  `adresse` varchar(250) NOT NULL,
  `num_tel` varchar(10) NOT NULL,
  `email` varchar(100) NOT NULL,
  `point` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`ID_client`, `nom`, `prenom`, `adresse`, `num_tel`, `email`, `point`) VALUES
(1, 'sd', 'sdf', 'sdf', '0404040404', '', 0),
(2, 'Bargui', 'Henda', '43 rue Royale   ', '0630403043', '', 0),
(3, 'Pinapple', 'Lola', '43 rue Royale   ', '0743535434', 'lola.pinapple@gmail.com', 13);

-- --------------------------------------------------------

--
-- Table structure for table `commande`
--

CREATE TABLE `commande` (
  `ID_Commande` int(11) NOT NULL,
  `IDRH` int(11) NOT NULL,
  `id_client` int(11) NOT NULL,
  `id_table` int(11) NOT NULL,
  `Date` date NOT NULL,
  `Statut` int(11) NOT NULL,
  `Type_Commande` int(11) NOT NULL,
  `Total_TTC` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `commande`
--

INSERT INTO `commande` (`ID_Commande`, `IDRH`, `id_client`, `id_table`, `Date`, `Statut`, `Type_Commande`, `Total_TTC`) VALUES
(1, 1, 1, 1, '2021-09-08', 1, 0, 12.5),
(2, 1, 2, 1, '2021-09-01', 1, 0, 10),
(3, 1, 1, 1, '2021-09-14', 1, 0, 10),
(4, 4, 2, 1, '2021-09-13', 1, 0, 10),
(5, 1, 2, 1, '2021-09-02', 1, 0, 10),
(6, 1, 2, 1, '2021-08-17', 1, 0, 10),
(7, 1, 2, 1, '2021-01-01', 1, 0, 10),
(8, 1, 2, 1, '2021-02-02', 1, 0, 10),
(9, 1, 2, 1, '2021-03-03', 1, 0, 10),
(10, 1, 2, 1, '2021-04-06', 1, 0, 10),
(11, 1, 2, 1, '2021-05-19', 1, 0, 10),
(12, 2, 1, 1, '2021-06-23', 1, 0, 12.5),
(13, 2, 3, 1, '2021-07-07', 1, 0, 12.5),
(14, 2, 1, 1, '2021-09-09', 1, 0, 12.5),
(15, 2, 1, 7, '2021-09-09', 1, 0, 12.5),
(16, 2, 1, 8, '2021-08-19', 1, 0, 12.5),
(17, 2, 1, 7, '2021-09-09', 1, 0, 12.5),
(18, 2, 1, 7, '2021-09-09', 1, 0, 12.5),
(23, 2, 1, 7, '2021-09-09', 0, 0, 12.5),
(24, 2, 1, 4, '2021-09-09', 1, 0, 12.5),
(25, 2, 1, 5, '2021-09-09', 1, 0, 12.5),
(26, 2, 1, 1, '2021-09-09', 1, 0, 12.5),
(27, 2, 1, 9, '2021-09-09', 1, 0, 25),
(28, 2, 1, 8, '2021-09-09', 1, 0, 12.5),
(29, 2, 1, 1, '2021-09-09', 1, 0, 12.5),
(30, 2, 1, 4, '2021-09-09', 1, 0, 100),
(31, 2, 1, 1, '2021-09-09', 1, 0, 12.5),
(32, 2, 1, 5, '2021-09-09', 1, 0, 12.5),
(33, 2, 1, 5, '2021-09-09', 1, 0, 12.5),
(34, 2, 1, 5, '2021-09-09', 1, 0, 25),
(35, 2, 1, 5, '2021-09-09', 1, 0, 112.5),
(36, 2, 1, 1, '2021-09-10', 1, 0, 15),
(37, 2, 1, 1, '2021-09-11', 1, 0, 25);

-- --------------------------------------------------------

--
-- Table structure for table `compte_user`
--

CREATE TABLE `compte_user` (
  `IDRH` int(11) NOT NULL,
  `Identifiant` varchar(50) NOT NULL,
  `Mot_de_passe` varchar(50) NOT NULL,
  `Habilitation` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `compte_user`
--

INSERT INTO `compte_user` (`IDRH`, `Identifiant`, `Mot_de_passe`, `Habilitation`) VALUES
(1, 'nguyen', 'nguyen', 1),
(2, 'le', 'le', 1),
(6, 'NB6', '123456', 2);

-- --------------------------------------------------------

--
-- Table structure for table `employe`
--

CREATE TABLE `employe` (
  `IDRH` int(11) NOT NULL,
  `Nom` varchar(100) NOT NULL,
  `Prenom` varchar(100) NOT NULL,
  `Date_naissance` date NOT NULL,
  `Adresse` varchar(250) NOT NULL,
  `Num_tel` varchar(10) NOT NULL,
  `Type_contrat` varchar(20) NOT NULL,
  `Date_debut_contrat` date NOT NULL,
  `Date_fin_contrat` date DEFAULT NULL,
  `Duree_hebdomadaire` int(11) NOT NULL,
  `Emploi` varchar(20) NOT NULL,
  `statut` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `employe`
--

INSERT INTO `employe` (`IDRH`, `Nom`, `Prenom`, `Date_naissance`, `Adresse`, `Num_tel`, `Type_contrat`, `Date_debut_contrat`, `Date_fin_contrat`, `Duree_hebdomadaire`, `Emploi`, `statut`) VALUES
(1, 'NGUYEN', 'NHAN', '2021-08-16', '55 RUE DE ROME', '3434934832', 'CDD', '2021-08-01', '2021-08-31', 35, 'Serveur(se)', 1),
(2, 'binh', 'BAO', '2021-08-09', '55 3434', '423523554', 'CDI', '2021-08-02', NULL, 23, 'Serveur(se)', 1),
(4, 'POPPI', 'POPPI', '2021-08-04', '2323', '24234', 'CDD', '2021-08-11', '2021-08-29', 323, 'Plongeur(se)', 0),
(5, 'QSDS', 'QSDSD', '2021-09-10', 'QSDSD', 'qsdsqd', 'CDD', '2021-09-03', '2021-09-09', 232, 'Caissier(e)', 0),
(6, 'YOSHI', 'YOSHI', '2021-09-16', '55 RUE DE ROME', '34324324', 'ALTERNANCE', '2021-09-10', '2021-09-30', 34, 'Caissier(e)', 1);

-- --------------------------------------------------------

--
-- Table structure for table `habilitation`
--

CREATE TABLE `habilitation` (
  `Habilitation` int(10) NOT NULL,
  `Libelle` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `habilitation`
--

INSERT INTO `habilitation` (`Habilitation`, `Libelle`) VALUES
(1, 'Admin'),
(2, 'User');

-- --------------------------------------------------------

--
-- Table structure for table `produit`
--

CREATE TABLE `produit` (
  `ID_Produit` int(11) NOT NULL,
  `Libelle` varchar(100) NOT NULL,
  `Libelle_categorie` varchar(100) NOT NULL,
  `Prix_unitaire` float NOT NULL,
  `Photo` varchar(255) NOT NULL,
  `valable` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `produit`
--

INSERT INTO `produit` (`ID_Produit`, `Libelle`, `Libelle_categorie`, `Prix_unitaire`, `Photo`, `valable`) VALUES
(2, 'Salad mangue crevette', 'Entree', 12.5, 'douong0.jpeg', 0),
(4, 'Salad mangue crevette', 'Entree', 12.5, 'douong0.jpeg', 0),
(5, 'Salade mozarella', 'Salade', 20, 'default.png', 1),
(6, 'Salade grec', 'Salade', 15, 'default.png', 1),
(7, '7 up ', 'Boisson', 4.5, 'douong3.jpeg', 1),
(8, 'Thé de pêche', 'Boisson', 6.8, 'douong9.jpg', 1),
(9, 'Baba au rhum', 'Dessert', 5, 'téléchargement.jpeg', 1),
(10, 'Boeuf ', 'Plat', 15, 'téléchargement (1).jpeg', 1),
(11, 'Banh cuon', 'Entree', 10, 'téléchargement (2).jpeg', 1),
(12, 'Coca', 'Boisson', 10, 'douong0.jpeg', 1);

-- --------------------------------------------------------

--
-- Table structure for table `produit_commande`
--

CREATE TABLE `produit_commande` (
  `ID_Commande` int(11) NOT NULL,
  `ID_Produit` int(11) NOT NULL,
  `Quantite` int(11) NOT NULL,
  `Prix_unitaire` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `produit_commande`
--

INSERT INTO `produit_commande` (`ID_Commande`, `ID_Produit`, `Quantite`, `Prix_unitaire`) VALUES
(1, 2, 1, 12.5),
(2, 2, 3, 2),
(3, 2, 4, 2),
(5, 5, 3, 10),
(6, 5, 4, 2),
(5, 6, 10, 30),
(5, 6, 10, 20),
(12, 4, 1, 12.5),
(13, 4, 1, 12.5),
(14, 4, 1, 12.5),
(15, 4, 1, 12.5),
(16, 4, 1, 12.5),
(17, 4, 1, 12.5),
(18, 4, 1, 12.5),
(18, 4, 1, 12.5),
(18, 4, 1, 12.5),
(18, 2, 1, 12.5),
(18, 4, 1, 12.5),
(23, 4, 1, 12.5),
(24, 4, 1, 12.5),
(25, 2, 1, 12.5),
(26, 4, 1, 12.5),
(27, 4, 2, 12.5),
(28, 4, 1, 12.5),
(29, 4, 1, 12.5),
(30, 2, 2, 12.5),
(31, 4, 1, 12.5),
(32, 2, 1, 12.5),
(33, 2, 1, 12.5),
(34, 2, 2, 12.5),
(35, 4, 1, 12.5),
(30, 11, 3, 10),
(30, 10, 3, 15),
(35, 11, 10, 10),
(36, 10, 1, 15),
(37, 11, 1, 10),
(37, 10, 1, 15);

-- --------------------------------------------------------

--
-- Table structure for table `tab`
--

CREATE TABLE `tab` (
  `id` int(11) NOT NULL,
  `libelle` varchar(100) NOT NULL,
  `statut` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tab`
--

INSERT INTO `tab` (`id`, `libelle`, `statut`) VALUES
(1, 'salle 1', 1),
(2, 'salle 4', 0),
(3, 'salle 1', 0),
(4, 'salle 2', 1),
(5, 'salle 3', 1),
(6, 'salle 4', 2),
(7, 'terrasse 1', 2),
(8, 'terrasse 2', 1),
(9, 'terrasse 3', 1),
(10, 'terrasse 4', 0),
(11, 'salle 10', 1);

-- --------------------------------------------------------

--
-- Table structure for table `type_contrat`
--

CREATE TABLE `type_contrat` (
  `Type_contrat` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `type_contrat`
--

INSERT INTO `type_contrat` (`Type_contrat`) VALUES
('ALTERNANCE'),
('CDD'),
('CDI'),
('INTERIM'),
('PRO');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `categorie`
--
ALTER TABLE `categorie`
  ADD PRIMARY KEY (`Libelle_categorie`);

--
-- Indexes for table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`ID_client`);

--
-- Indexes for table `commande`
--
ALTER TABLE `commande`
  ADD PRIMARY KEY (`ID_Commande`),
  ADD KEY `IDRH` (`IDRH`),
  ADD KEY `FK_client` (`id_client`),
  ADD KEY `FK_tab` (`id_table`);

--
-- Indexes for table `compte_user`
--
ALTER TABLE `compte_user`
  ADD UNIQUE KEY `IDRH` (`IDRH`) USING BTREE,
  ADD KEY `FK_Habilitation` (`Habilitation`);

--
-- Indexes for table `employe`
--
ALTER TABLE `employe`
  ADD PRIMARY KEY (`IDRH`),
  ADD KEY `FK_type_contrat` (`Type_contrat`);

--
-- Indexes for table `habilitation`
--
ALTER TABLE `habilitation`
  ADD PRIMARY KEY (`Habilitation`);

--
-- Indexes for table `produit`
--
ALTER TABLE `produit`
  ADD PRIMARY KEY (`ID_Produit`),
  ADD KEY `FK_Libelle_Categorie` (`Libelle_categorie`);

--
-- Indexes for table `produit_commande`
--
ALTER TABLE `produit_commande`
  ADD KEY `FK_ID_PRODUIT` (`ID_Produit`),
  ADD KEY `ID_Commande` (`ID_Commande`) USING BTREE;

--
-- Indexes for table `tab`
--
ALTER TABLE `tab`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `type_contrat`
--
ALTER TABLE `type_contrat`
  ADD PRIMARY KEY (`Type_contrat`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `client`
--
ALTER TABLE `client`
  MODIFY `ID_client` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `commande`
--
ALTER TABLE `commande`
  MODIFY `ID_Commande` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- AUTO_INCREMENT for table `employe`
--
ALTER TABLE `employe`
  MODIFY `IDRH` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `habilitation`
--
ALTER TABLE `habilitation`
  MODIFY `Habilitation` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `produit`
--
ALTER TABLE `produit`
  MODIFY `ID_Produit` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `tab`
--
ALTER TABLE `tab`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `commande`
--
ALTER TABLE `commande`
  ADD CONSTRAINT `FK_IDRH` FOREIGN KEY (`IDRH`) REFERENCES `employe` (`IDRH`),
  ADD CONSTRAINT `FK_client` FOREIGN KEY (`id_client`) REFERENCES `client` (`ID_client`),
  ADD CONSTRAINT `FK_tab` FOREIGN KEY (`id_table`) REFERENCES `tab` (`id`);

--
-- Constraints for table `compte_user`
--
ALTER TABLE `compte_user`
  ADD CONSTRAINT `FK_Habilitation` FOREIGN KEY (`Habilitation`) REFERENCES `habilitation` (`Habilitation`),
  ADD CONSTRAINT `FK_IDRH_1` FOREIGN KEY (`IDRH`) REFERENCES `employe` (`IDRH`);

--
-- Constraints for table `employe`
--
ALTER TABLE `employe`
  ADD CONSTRAINT `FK_type_contrat` FOREIGN KEY (`Type_contrat`) REFERENCES `type_contrat` (`Type_contrat`);

--
-- Constraints for table `produit`
--
ALTER TABLE `produit`
  ADD CONSTRAINT `FK_Libelle_Categorie` FOREIGN KEY (`Libelle_categorie`) REFERENCES `categorie` (`Libelle_categorie`);

--
-- Constraints for table `produit_commande`
--
ALTER TABLE `produit_commande`
  ADD CONSTRAINT `FK_ID_COMMANDE` FOREIGN KEY (`ID_Commande`) REFERENCES `commande` (`ID_Commande`),
  ADD CONSTRAINT `FK_ID_PRODUIT` FOREIGN KEY (`ID_Produit`) REFERENCES `produit` (`ID_Produit`);
