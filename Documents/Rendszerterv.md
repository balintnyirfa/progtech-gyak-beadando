# Rendszerterv

## 1. A rendszer célja
A rendszer célja az, hogy lehetővé tegye a felhasználók számára, hogy interaktívan megjelenítsék a naptárt, és azon navigáljanak az időben. A program lehetővé    teszi a felhasználók számára, hogy különböző naptárakat tudjon létrehozni (napi, heti, havi). Illetve lehetőséget ad arra, hogy eseményeket hozzon létre, töröljön és módosítani is tudja a már meglévőeket.
A program célja az is, hogy egyszerűen használható legyen, és kényelmesen kezelhető legyen a felhasználók számára. Ezen felül, a program segítségével a felhasználók könnyen áttekinthetik a fontos dátumokat és eseményeket, így a program lehetővé teszi a hatékonyabb időtervezést és szervezést.

## 2. Projektterv
Ezt az alkalmazást Java nyelven valósítjuk meg, melyhez társítunk egy grafikus felületet is. Egyaránt dolgozunk mindannyian front- és backenden is.
- A frontend-el azt szeretnénk elérni, hogy egy könnyen átlátható, de mégis szép design-t kapjon az app.
- A backend célja, hogy a háttérben zajló folyamatokat le tudjuk programozni, Java kód megírása, a program helyes működését biztosítjuk.

## 3. Üzleti folyamatok modellje

![prog_tech-uzleti_folyamatok_terve](https://github.com/balintnyirfa/progtech-gyak-beadando/assets/78541351/bde13a6e-10d5-4d30-878b-562afbc03f62)

## 4. Követelmények
 - Felhasználók, naptár adatok tárolása adatbázisban
 - Napi, heti, havi naptár létrehozása
 - Események módosítása, törlése, létrehozása
 - Hozzáadott események megjelenítése
 - Esemény közeledtével a felhasználó értesítése
 - Adatvédelem

## 5. Funkcionális terv
- Vendég: Lényegében csak regisztrálni tud és az app további részéhez nem tud hozzáférni, amíg ez a feltétel nem teljesül és nem jelentkezik be a fiókjával.
- Felhasználó: Sikeres regisztráció és bejelentkezés után hozzá tud férni az alkalmazás nyújtotta szolgáltatásokhoz. Tud a naptárhoz új eseményeket rendelni, amiket később el tud érni és tudja ezeket módosítani.

## 6. Fizikai környezet
Az igénybevételéhez szükség van regisztrációhoz, anélkül nem lehet hozzáférni az app által nyújtott szolgáltatásokhoz.
- Van tűzfal a hálózaton, és minden portot engedélyez.
- Nincsenek megvásárolt komponenseink
- Fejlesztői eszközök:
 - IntelliJ IDEA 2023.1.1

## 7. Adatbázis terv

![image](https://github.com/balintnyirfa/progtech-gyak-beadando/assets/99401028/7a2ab44f-5d80-4b53-87aa-f089a71d6c38)

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
- Egyes bugok javítása
- Design korszerűsítése
- Újabb, frissebb megoldások implementálása
