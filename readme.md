<h1>"Oddam w dobre ręce"</h1>

Celem projektu jest stworzenie miejsca, w którym każdy będzie mógł oddać niepotrzebne rzeczy zaufanym instytucjom.

<h2>Wykorzystane technologie</h2>

<ul>
  <li>Spring Boot</li>
  <li>Spring Security</li>
  <li>Spring Data/Jpa</li>
  <li>Spring Form</li>
  <li>MySQL</li>
  <li>JUnit</li>
  <li>Lombok</li>
</ul>

<h3>Wymagane funkcjonalności</h3>

0. 
- *Landing page*, który ma zachęcać do skorzystania z aplikacji.
- Dodawanie darów
- role użytkowników (ROLE_USER, ROLE_ADMIN)

1. Profil administratora:
 - panel adminstracyjny
 - logowanie,
 - zarządzanie (**CRUD**) administratorami,
 - zarządzanie (**CRUD**) zaufanymi instytucjami,
 - przeglądanie, edycja, usuwanie zarejestrowanych użytkowników,
 - przegląd przekazanych darów.
 -  możliwością określenia statusów darów (złożone/odebrane/przekazane)
2. Profil użytkownika:
 - rejestracja,
 - walidacja poprawnego podania dwóch identycznych haseł,
 - logowanie,
 - edycja własnego profilu,
 - dodawanie darów tylko po zalogowaniu,
 - przeglądanie, edycja, kasowanie przekazanych darów,
 - zaznaczenie, że dar został komuś oddany (archiwizacja).

-----------------------------------------------------------------

 <h4>W trakcie realizacji</h4>
 
- potwierdzenie aktywacji konta poprzez wiadomość przesłaną na email podany przy rejestracji,
- obsługa zapomnianych haseł,
- walidacja obsługi administratorów (np. brak możliwości skasowania ostatniego istniejącego administratora).
