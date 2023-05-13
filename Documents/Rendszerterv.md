# Rendszerterv

## 1. A rendszer célja
A rendszer célja az, hogy lehetővé tegye a felhasználók számára, hogy interaktívan megjelenítsék a naptárt, és azon navigáljanak az időben. A program lehetővé    teszi a felhasználók számára, hogy különböző naptárakat tudjon létrehozni (napi, heti, havi). Illetve lehetőséget ad arra, hogy eseményeket hozzon létre, töröljön és módosítani is tudja a már meglévőeket.
A program célja az is, hogy egyszerűen használható legyen, és kényelmesen kezelhető legyen a felhasználók számára. Ezen felül, a program segítségével a felhasználók könnyen áttekinthetik a fontos dátumokat és eseményeket, így a program lehetővé teszi a hatékonyabb időtervezést és szervezést.

## 2. Projektterv

## 3. Üzleti folyamatok modellje

## 4. Követelmények
 - Felhasználók, naptár adatok tárolása adatbázisban
 - Napi, heti, havi naptár létrehozása
 - Események módosítása, törlése, létrehozása
 - Hozzáadott események megjelenítése
 - Esemény közeledtével a felhasználó értesítése
 - Adatvédelem

## 5. Funkcionális terv 

## 6. Fizikai környezet

## 7. Adatbázis terv

![database](https://github.com/balintnyirfa/progtech-gyak-beadando/assets/99401028/802f1634-d952-4dbf-8b3f-fad17e4e0a81)

## 8. Tesztterv 
Az alkalmazás teszteléséhez **unit teszteket** fogunk alkalmazni, ami a programkód zömét lefedi. 
A tesztek elsődleges célja az eddig meglévő funkcióknak, metódusoknak tesztelése.

- Adatbázis tesztelése
- Adatok beolvasása és visszaadása
- Linkek és gombok tesztelése
- CRUD műveletek tesztelése
- Biztonság ellenőrzése (megfelelően vannak-e védve az adatok, jelszó titkosítva van-e, kijelentkezés ellenőrzése)
- Hibaüzenetek tesztelése (felhasználó által bevitt hibás adatok esetén)


| Teszt neve                          | Eredmény       |
|-------------------------------------|----------------|
| `Regisztráció helyes adatokkal`     |                |
| `Regisztráció helytelen adatokkal`  |                |
| `Bejelentkezés helyes adatokkal`    |                |
| `Bejelentkezés helytelen adatokkal` |                |
| `Naptár létrehozása`                |                |
| `Naptár megjelenítése`              |                |
| `Esemény létrehozása`               |                |
| `Esemény módosítása`                |                |
| `Esemény törlése`                   |                |
| `Események megjelenítése`           |                |
| `Kijelentkezés`                     |                |

## 9. Telepítési terv
Nem szükséges telepítés az alkalmazás használatához.

## 10. Implementációs terv
Az alkalmazást Java programnyelven implementáljuk, a felhasználók adatait MySQL adatbázisban fogjuk eltárolni, phpMyAdmin-t használva.

### Funkciók:

- Regisztráció
- Bejelentkezés
- Kijelentkezés
- Jelszóváltoztatás
- Naptár létrehozása, megtekintése
- Esemény létrehozása, megtekintése, módosítása, törlése
- Felhasználó értesítése adott eseményről

## 11. Karbantartási terv
