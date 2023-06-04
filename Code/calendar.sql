-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Gép: 127.0.0.1
-- Létrehozás ideje: 2023. Jún 04. 15:03
-- Kiszolgáló verziója: 10.4.28-MariaDB
-- PHP verzió: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Adatbázis: `calendar`
--

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `calendar`
--

CREATE TABLE `calendar` (
  `ID` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `type` enum('NAPI','HETI','HAVI') NOT NULL,
  `from_date` date NOT NULL,
  `to_date` date NOT NULL,
  `title` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- A tábla adatainak kiíratása `calendar`
--

INSERT INTO `calendar` (`ID`, `user_id`, `type`, `from_date`, `to_date`, `title`) VALUES
(14, 2, 'NAPI', '2023-06-04', '2023-06-04', 'Június negyedikei teendők');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `event`
--

CREATE TABLE `event` (
  `ID` int(11) NOT NULL,
  `calendar_id` int(11) NOT NULL,
  `title` varchar(100) NOT NULL,
  `content` varchar(1000) DEFAULT NULL,
  `from_date` datetime NOT NULL,
  `to_date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- A tábla adatainak kiíratása `event`
--

INSERT INTO `event` (`ID`, `calendar_id`, `title`, `content`, `from_date`, `to_date`) VALUES
(29, 14, 'Progtech beadandó', 'Meg kell csinálni a beadandót', '2023-06-04 08:00:00', '2023-06-04 18:00:00'),
(30, 14, 'Hálózat vizsga', 'Hálózat vizsgára készülés :)', '2023-06-04 09:00:00', '2023-06-04 14:00:00');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `user`
--

CREATE TABLE `user` (
  `ID` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `firstname` varchar(100) NOT NULL,
  `lastname` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- A tábla adatainak kiíratása `user`
--

INSERT INTO `user` (`ID`, `username`, `firstname`, `lastname`, `email`, `password`) VALUES
(2, 'Nelli', 'Deli', 'Daniella', 'nelli@gmail.com', '81dc9bdb52d04dc20036dbd8313ed055'),
(4, 'Lili', 'Kiss', 'Lili', 'kl@gmail.com', 'abc0be56f538db4d8cbff4d1caf7980d');

--
-- Indexek a kiírt táblákhoz
--

--
-- A tábla indexei `calendar`
--
ALTER TABLE `calendar`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_calendar_user` (`user_id`);

--
-- A tábla indexei `event`
--
ALTER TABLE `event`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_event_calendar` (`calendar_id`);

--
-- A tábla indexei `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`ID`);

--
-- A kiírt táblák AUTO_INCREMENT értéke
--

--
-- AUTO_INCREMENT a táblához `calendar`
--
ALTER TABLE `calendar`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT a táblához `event`
--
ALTER TABLE `event`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT a táblához `user`
--
ALTER TABLE `user`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Megkötések a kiírt táblákhoz
--

--
-- Megkötések a táblához `calendar`
--
ALTER TABLE `calendar`
  ADD CONSTRAINT `FK_calendar_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`ID`);

--
-- Megkötések a táblához `event`
--
ALTER TABLE `event`
  ADD CONSTRAINT `FK_event_calendar` FOREIGN KEY (`calendar_id`) REFERENCES `calendar` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
