INSERT IGNORE INTO charity_donation.categories (id, name) VALUES (1, 'ubrania');
INSERT IGNORE INTO charity_donation.categories (id, name) VALUES (2, 'zabawki');
INSERT IGNORE INTO charity_donation.categories (id, name) VALUES (3, 'elektronika');
INSERT IGNORE INTO charity_donation.categories (id, name) VALUES (4, 'książki');
INSERT IGNORE INTO charity_donation.categories (id, name) VALUES (5, 'inne');
INSERT IGNORE INTO charity_donation.donation_status (id, status) VALUES (1,'Nieodebrane');
INSERT IGNORE INTO charity_donation.donation_status (id, status) VALUES (2,'Odebrane');
INSERT IGNORE INTO charity_donation.institutions (id, description, name) VALUES (1, 'Cel i misja: Pomoc dzieciom z ubogich rodzin.', 'Fundacja "Dbam o Zdrowie"');
INSERT IGNORE INTO charity_donation.institutions (id, description, name) VALUES (2, 'Cel i misja: Pomoc wybudzaniu dzieci ze spiaczki.', 'Fundacja "A kogo"');
INSERT IGNORE INTO charity_donation.institutions (id, description, name) VALUES (3, 'Cel i misja: Pomoc osobom znajdujacym sie w trudnej sytuacji zyciowej.', 'Fundacja “Dla dzieci"');
INSERT IGNORE INTO charity_donation.institutions (id, description, name) VALUES (4, 'Cel i misja: Pomoc dla osob nie posiadajacych miejsca zamieszkania', 'Fundacja “Bez domu”');
INSERT IGNORE INTO charity_donation.roles (id, name) VALUES (1,'ROLE_USER');
INSERT IGNORE INTO charity_donation.roles (id, name) VALUES (2,'ROLE_ADMIN');
INSERT IGNORE INTO charity_donation.roles (id, name) VALUES (3,'ROLE_ORGANISATION');