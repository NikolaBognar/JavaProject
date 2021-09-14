-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 04, 2021 at 01:40 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bioskop`
--
CREATE DATABASE IF NOT EXISTS `bioskop` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `bioskop`;

-- --------------------------------------------------------

--
-- Table structure for table `bioskop`
--

CREATE TABLE `bioskop` (
  `idbioskop` int(11) NOT NULL,
  `naziv` varchar(45) NOT NULL,
  `grad` varchar(45) DEFAULT NULL,
  `slika` varchar(45) DEFAULT NULL,
  `tehnologije` varchar(45) DEFAULT NULL,
  `radnoVreme` varchar(80) DEFAULT NULL,
  `kontakt` varchar(80) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bioskop`
--

INSERT INTO `bioskop` (`idbioskop`, `naziv`, `grad`, `slika`, `tehnologije`, `radnoVreme`, `kontakt`) VALUES
(1, 'Bioskop Narodni', 'Sombor', 'img/bioskopSo.jpg', '2D i 3D', 'Svaki dan 15-23h', 'Kralja Petra I br.4 025-412056'),
(2, 'Cinestar Novi Sad', 'Novi Sad', 'img/bioskopNs.jpg', '2D i 3D', 'Radni danima od 15:00 do 23:00, a vikendom od 11:00 do 00:00', 'Sentandrejski put 11, \r\nBIG CEE Novi Sad, \r\nNovi Sad 21000'),
(3, 'Bioskop Fontana', 'Beograd', 'img/bioskopBg.jpg', '2D i 3D', 'Svakim danom 09-21h', 'Pariske komune 13, Beograd 11070');

-- --------------------------------------------------------

--
-- Table structure for table `clanstvo`
--

CREATE TABLE `clanstvo` (
  `idclanstvo` int(11) NOT NULL,
  `naziv` varchar(45) NOT NULL,
  `pogodnosti` varchar(200) NOT NULL,
  `clanarina` double DEFAULT NULL,
  `slika` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `clanstvo`
--

INSERT INTO `clanstvo` (`idclanstvo`, `naziv`, `pogodnosti`, `clanarina`, `slika`) VALUES
(1, 'Deciji klub', 'Za decu do 12 godina besplatne kokice!', 500, 'img/decijiklub.jpg'),
(2, 'Senior klub', 'Popust 20%  na sve projekcije!', 1000, 'img/senior.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `clanstvo_korisnik`
--

CREATE TABLE `clanstvo_korisnik` (
  `idclanstvo_korisnik` int(11) NOT NULL,
  `naziv` varchar(45) DEFAULT NULL,
  `imeKorisnika` varchar(45) DEFAULT NULL,
  `datum` varchar(45) DEFAULT NULL,
  `slika` varchar(45) DEFAULT NULL,
  `id_clanstvo` int(11) NOT NULL,
  `id_korisnik` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `clanstvo_korisnik`
--

INSERT INTO `clanstvo_korisnik` (`idclanstvo_korisnik`, `naziv`, `imeKorisnika`, `datum`, `slika`, `id_clanstvo`, `id_korisnik`) VALUES
(24, 'Senior klub', 'nikola', '2021-04-03', 'img/seniorKlub.jpg', 2, 16),
(25, 'Deciji klub', 'nikola', '2021-04-04', 'img/decijiklub.jpg', 1, 16);

-- --------------------------------------------------------

--
-- Table structure for table `film`
--

CREATE TABLE `film` (
  `idFilm` int(11) NOT NULL,
  `naziv` varchar(45) NOT NULL,
  `trajanje` varchar(45) NOT NULL,
  `opis` varchar(600) DEFAULT NULL,
  `trailer` varchar(400) DEFAULT NULL,
  `slika` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `film`
--

INSERT INTO `film` (`idFilm`, `naziv`, `trajanje`, `opis`, `trailer`, `slika`) VALUES
(1, 'Izgubljeni na horizontu', '87 min.', 'Ovo je trebalo da bude samo rutinski 99-minutni let do ostrva na kom se njihovi prijatelji vencavaju. Medjutim, samo nekoliko minuta nakon poletanja, njihov pilot doživljava srcani udar. Ne znaju gde se nalaze, ne znaju da upravljaju avionom, niti da slete.', '<iframe width=\"600\" height=\"340\" src=\"https://www.youtube.com/embed/KldiQVsfJpw\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>', 'img/izgubljeni.jpg'),
(2, 'Godzila protiv Konga', '114 min.', 'Svet je spreman za jos jednu borbu giganta. Kralj cudovišta i kralj Ostrva lobanja krecu jedan na drugog u borbi za nadmocnu monstruoznu superiornost.', '<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/odM92ap8_c0\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>', 'img/godzila.jpg'),
(3, 'Stela', '91 min.', 'Nakon ocajnickog pokusaja da probudi paznju svojih roditelja, 11 -godišnji decak Nikola bezi od kuce. Stela, pas sarplaninac, je jedina koja moze da nadje put do njegovog srca i vrati mu veru u život.', '<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/CQo6vf9wDS8&feature=emb_title\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>', 'img/stela.jpg'),
(4, 'Tom and Jerry', '101 min.', 'Jedno od najomiljenijih rivalstava u istoriji ponovo ozivljava kad se Dzeri useli u najlepsi hotel u Njujorku uoci “vencanja veka”, prisiljavajuci ocajnog organizatora vencanja da hitno zatrazi nuzne mere.', '<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/kP9TfCWaQT4\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>', 'img/tomidzeri.jpg'),
(5, 'Bezi!', '90 min.', 'Nesto je zlokobno i neprirodno u odnosu majke Dijane (Sara Paulson) i cerke Kloe (Kijera Alen). Prezastitnicki nastrojena Dijana je od rodjenja odgajala cerku u potpunoj izolaciji od sveta. Kada Kloe odraste i postane mlada zena, pocinje da otkriva da stvari u njihovoj kuci nisu onakve kakve se cine.', '<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/0Dhh7q9Us5c\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>', 'img/bezi.jpg'),
(6, 'Dusa', '92 min.', 'U 2020. godini Pixar Animation Studios vodi nas na putovanje od ulica Njujorka do kosmickih kraljevstava, kako bismo otkrili odgovore na najvaznija pitanja u zivotu. U reziji dvostrukog dobitnika oskara, Pita Doktera (“Do neba”, “U mojoj glavi”), film “DUSA” u nase bioskope stize 19. novembra 2020. godine.', '<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/xOsLIiBStEs\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>', 'img/dusa.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `film_repertoar`
--

CREATE TABLE `film_repertoar` (
  `id` int(11) NOT NULL,
  `datum` date NOT NULL,
  `vreme` time NOT NULL,
  `cena` double DEFAULT NULL,
  `nazivFilma` varchar(45) NOT NULL,
  `tehnologija` varchar(45) DEFAULT NULL,
  `filmId` int(11) NOT NULL,
  `repertoarId` int(11) NOT NULL,
  `IdSala` int(11) NOT NULL,
  `idBioskop` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `film_repertoar`
--

INSERT INTO `film_repertoar` (`id`, `datum`, `vreme`, `cena`, `nazivFilma`, `tehnologija`, `filmId`, `repertoarId`, `IdSala`, `idBioskop`) VALUES
(1, '2021-03-27', '19:00:00', 300, 'Izgubljeni na horizontu', '2D', 1, 1, 1, 1),
(2, '2021-03-30', '17:00:00', 400, 'Godzila protiv Konga', '3D', 2, 1, 2, 2),
(9, '2021-04-01', '14:00:00', 350, 'Stela', '2D', 3, 1, 1, 1),
(10, '2021-04-01', '13:00:00', 400, 'Tom and Jerry', '3D', 4, 1, 2, 2),
(11, '2021-04-02', '20:00:00', 300, 'Bezi', '2D', 5, 1, 1, 1),
(12, '2021-04-02', '14:00:00', 450, 'Dusa', '3D', 6, 1, 2, 2);

-- --------------------------------------------------------

--
-- Table structure for table `karta`
--

CREATE TABLE `karta` (
  `id` int(11) NOT NULL,
  `nazivFilma` varchar(45) NOT NULL,
  `datum` date NOT NULL,
  `vreme` time NOT NULL,
  `tehnologija` varchar(45) NOT NULL,
  `cena` double NOT NULL,
  `idProjekcije` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `korisnik`
--

CREATE TABLE `korisnik` (
  `idKorisnik` int(11) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `ime` varchar(45) NOT NULL,
  `prezime` varchar(45) NOT NULL,
  `datum` varchar(45) NOT NULL,
  `pol` varchar(45) NOT NULL,
  `tip` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `poeni` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `korisnik`
--

INSERT INTO `korisnik` (`idKorisnik`, `username`, `password`, `ime`, `prezime`, `datum`, `pol`, `tip`, `email`, `poeni`) VALUES
(1, 'admin', 'nikola123', 'admin', 'adminc', '1997-02-11', 'musko', 'admin', 'nikola97@gmail.com', 0),
(4, 'menadzer', 'menadzer', 'Menadzer', 'menadzer', '1994-02-11', 'musko', 'menadzer', 'menadzer1@gmail.com', 0),
(6, 'milos', 'miki', 'Milos', 'Radojev', '2005-10-10', 'musko', 'korisnik', 'milos12@gmail.com', 0),
(16, 'nikolab', '1234', 'nikola', 'bognar', '1997-11-02', 'musko', 'korisnik', 'bognarnikola@gmail.com', 0);

-- --------------------------------------------------------

--
-- Table structure for table `repertoar`
--

CREATE TABLE `repertoar` (
  `id` int(11) NOT NULL,
  `naziv` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `repertoar`
--

INSERT INTO `repertoar` (`id`, `naziv`) VALUES
(1, 'Repertoar');

-- --------------------------------------------------------

--
-- Table structure for table `rezervacija`
--

CREATE TABLE `rezervacija` (
  `id` int(11) NOT NULL,
  `username` varchar(45) DEFAULT NULL,
  `imePrezime` varchar(45) DEFAULT NULL,
  `nazivFilma` varchar(45) DEFAULT NULL,
  `sala` int(11) DEFAULT NULL,
  `datum` varchar(45) DEFAULT NULL,
  `vreme` varchar(45) DEFAULT NULL,
  `sedista` varchar(45) DEFAULT NULL,
  `idProj` int(11) DEFAULT NULL,
  `idKorisnik` int(11) DEFAULT NULL,
  `idBioskop` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rezervacija`
--

INSERT INTO `rezervacija` (`id`, `username`, `imePrezime`, `nazivFilma`, `sala`, `datum`, `vreme`, `sedista`, `idProj`, `idKorisnik`, `idBioskop`) VALUES
(49, 'menadzer', 'Menadzer menadzer', 'Bezi', 1, '2021-04-02', '20:00:00', 'A2 ', 11, 4, 1);

-- --------------------------------------------------------

--
-- Table structure for table `sala`
--

CREATE TABLE `sala` (
  `id` int(11) NOT NULL,
  `naziv` varchar(45) DEFAULT NULL,
  `tehnologija` varchar(45) DEFAULT NULL,
  `brojSedista` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sala`
--

INSERT INTO `sala` (`id`, `naziv`, `tehnologija`, `brojSedista`) VALUES
(1, 'Sala 1', '2D', 15),
(2, 'Sala 2', '3D', 15);

-- --------------------------------------------------------

--
-- Table structure for table `sediste`
--

CREATE TABLE `sediste` (
  `id` int(11) NOT NULL,
  `vrsta` varchar(45) NOT NULL,
  `rezervisano` varchar(45) NOT NULL,
  `oznaka` varchar(45) NOT NULL,
  `salaId` int(11) NOT NULL,
  `red` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sediste`
--

INSERT INTO `sediste` (`id`, `vrsta`, `rezervisano`, `oznaka`, `salaId`, `red`) VALUES
(1, 'obicno', 'ne', 'A1', 1, 'A'),
(2, 'obicno', 'da', 'A2', 1, 'A'),
(3, 'obicno', 'ne', 'A3', 1, 'A'),
(4, 'obicno', 'ne', 'A4', 1, 'A'),
(5, 'obicno', 'ne', 'A5', 1, 'A'),
(6, 'obicno', 'ne', 'B1', 1, 'B'),
(7, 'obicno', 'ne', 'B2', 1, 'B'),
(8, 'obicno', 'ne', 'B3', 1, 'B'),
(9, 'obicno', 'ne', 'B4', 1, 'B'),
(10, 'obicno', 'ne', 'B5', 1, 'B'),
(11, 'vip', 'ne', 'C1', 1, 'C'),
(12, 'vip', 'da', 'C2', 1, 'C'),
(13, 'vip', 'da', 'C3', 1, 'C'),
(14, 'vip', 'ne', 'C4', 1, 'C'),
(15, 'obicno', 'ne', 'C5', 1, 'C'),
(16, 'obicno', 'ne', 'A1', 2, 'A'),
(17, 'obicno', 'ne', 'A2', 2, 'A'),
(18, 'obicno', 'ne', 'A3', 2, 'A'),
(19, 'obicno', 'ne', 'A4', 2, 'A'),
(20, 'obicno', 'ne', 'A5', 2, 'A'),
(21, 'obicno', 'ne', 'B1', 2, 'B'),
(22, 'obicno', 'ne', 'B2', 2, 'B'),
(23, 'obicno', 'ne', 'B3', 2, 'B'),
(24, 'obicno', 'ne', 'B4', 2, 'B'),
(25, 'obicno', 'ne', 'B5', 2, 'B'),
(26, 'vip', 'ne', 'C1', 2, 'C'),
(27, 'vip', 'ne', 'C2', 2, 'C'),
(28, 'vip', 'ne', 'C3', 2, 'C'),
(29, 'vip', 'ne', 'C4', 2, 'C'),
(30, 'vip', 'da', 'C5', 2, 'C');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bioskop`
--
ALTER TABLE `bioskop`
  ADD PRIMARY KEY (`idbioskop`);

--
-- Indexes for table `clanstvo`
--
ALTER TABLE `clanstvo`
  ADD PRIMARY KEY (`idclanstvo`);

--
-- Indexes for table `clanstvo_korisnik`
--
ALTER TABLE `clanstvo_korisnik`
  ADD PRIMARY KEY (`idclanstvo_korisnik`),
  ADD KEY `id_korisnik_idx` (`id_korisnik`),
  ADD KEY `id_clanstvo_idx` (`id_clanstvo`);

--
-- Indexes for table `film`
--
ALTER TABLE `film`
  ADD PRIMARY KEY (`idFilm`);

--
-- Indexes for table `film_repertoar`
--
ALTER TABLE `film_repertoar`
  ADD PRIMARY KEY (`id`),
  ADD KEY `filmId_idx` (`filmId`),
  ADD KEY `repertoarId_idx` (`repertoarId`),
  ADD KEY `IdSala_idx` (`IdSala`),
  ADD KEY `idBioskop_idx` (`idBioskop`);

--
-- Indexes for table `karta`
--
ALTER TABLE `karta`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idProjekcije_idx` (`idProjekcije`);

--
-- Indexes for table `korisnik`
--
ALTER TABLE `korisnik`
  ADD PRIMARY KEY (`idKorisnik`);

--
-- Indexes for table `repertoar`
--
ALTER TABLE `repertoar`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `rezervacija`
--
ALTER TABLE `rezervacija`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idProj_idx` (`idProj`),
  ADD KEY `idKorisnik_idx` (`idKorisnik`),
  ADD KEY `idBioskop_idx` (`idBioskop`);

--
-- Indexes for table `sala`
--
ALTER TABLE `sala`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sediste`
--
ALTER TABLE `sediste`
  ADD PRIMARY KEY (`id`),
  ADD KEY `salaId_idx` (`salaId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bioskop`
--
ALTER TABLE `bioskop`
  MODIFY `idbioskop` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `clanstvo`
--
ALTER TABLE `clanstvo`
  MODIFY `idclanstvo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `clanstvo_korisnik`
--
ALTER TABLE `clanstvo_korisnik`
  MODIFY `idclanstvo_korisnik` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `film`
--
ALTER TABLE `film`
  MODIFY `idFilm` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `film_repertoar`
--
ALTER TABLE `film_repertoar`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `karta`
--
ALTER TABLE `karta`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `korisnik`
--
ALTER TABLE `korisnik`
  MODIFY `idKorisnik` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `repertoar`
--
ALTER TABLE `repertoar`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `rezervacija`
--
ALTER TABLE `rezervacija`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=50;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `clanstvo_korisnik`
--
ALTER TABLE `clanstvo_korisnik`
  ADD CONSTRAINT `id_clanstvo` FOREIGN KEY (`id_clanstvo`) REFERENCES `clanstvo` (`idclanstvo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `id_korisnik` FOREIGN KEY (`id_korisnik`) REFERENCES `korisnik` (`idKorisnik`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `film_repertoar`
--
ALTER TABLE `film_repertoar`
  ADD CONSTRAINT `IdSala` FOREIGN KEY (`IdSala`) REFERENCES `sala` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `filmId` FOREIGN KEY (`filmId`) REFERENCES `film` (`idFilm`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `idBioskop` FOREIGN KEY (`idBioskop`) REFERENCES `bioskop` (`idbioskop`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `repertoarId` FOREIGN KEY (`repertoarId`) REFERENCES `repertoar` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `karta`
--
ALTER TABLE `karta`
  ADD CONSTRAINT `idProjekcije` FOREIGN KEY (`idProjekcije`) REFERENCES `film_repertoar` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `rezervacija`
--
ALTER TABLE `rezervacija`
  ADD CONSTRAINT `idKorisnik` FOREIGN KEY (`idKorisnik`) REFERENCES `korisnik` (`idKorisnik`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `idProj` FOREIGN KEY (`idProj`) REFERENCES `film_repertoar` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `sediste`
--
ALTER TABLE `sediste`
  ADD CONSTRAINT `salaId` FOREIGN KEY (`salaId`) REFERENCES `sala` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
