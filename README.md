# Szervízkönyv Alkalmazás

## Projekt struktúra
```
service-book-complete/
  backend/    ← Spring Boot + H2 adatbázis
  frontend/   ← Angular 17 + Material UI
```

---

## BACKEND INDÍTÁSA (IntelliJ)

1. **Nyisd meg IntelliJ-ben:**
   - File → Open → `backend` mappa

2. **Build és run:**
   - Maven panel → Lifecycle → `clean` majd `package -DskipTests`
   - Vagy: Run → `ServiceBookApplication`

3. **Backend elérhető:** http://localhost:8080
4. **H2 Console:** http://localhost:8080/h2-console
   - JDBC URL: `jdbc:h2:mem:servicebook_db`
   - Username: `sa` | Password: (üres)

---

## FRONTEND INDÍTÁSA

1. **Nyisd meg terminálban a frontend mappát:**
```bash
cd frontend
npm install
npm start
```

2. **Frontend elérhető:** http://localhost:4200

---

## HASZNÁLAT

1. Nyisd meg: http://localhost:4200
2. **Regisztrálj** új fiókkal (Register gomb)
3. **Dashboard** - itt látod az autóidat
4. **+ Új autó** gombbal add hozzá a Honda Civiced
5. Az autóra kattintva látod a **szervíztörténetet**
6. **+ Szervíz hozzáadása** gombbal rögzíts bejegyzést

---

## TECHNOLÓGIÁK

**Backend:**
- Java 17 + Spring Boot 3.2
- Spring Security + JWT autentikáció
- H2 in-memory adatbázis
- Lombok

**Frontend:**
- Angular 17
- Angular Material UI
- TypeScript
- RxJS

---

## API VÉGPONTOK

| Metódus | Endpoint | Leírás |
|---------|----------|--------|
| POST | /api/auth/register | Regisztráció |
| POST | /api/auth/login | Bejelentkezés |
| GET | /api/cars | Saját autók listája |
| POST | /api/cars | Új autó hozzáadása |
| GET | /api/cars/{id} | Autó részletei |
| DELETE | /api/cars/{id} | Autó törlése |
| GET | /api/cars/{id}/records | Szervízbejegyzések |
| POST | /api/cars/{id}/records | Új bejegyzés |
| DELETE | /api/cars/{id}/records/{rid} | Bejegyzés törlése |
