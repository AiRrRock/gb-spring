CREATE TABLE users (
  id                    bigserial,
  username              VARCHAR(30) NOT NULL UNIQUE,
  password              VARCHAR(80) NOT NULL,
  email                 VARCHAR(50) UNIQUE,
  PRIMARY KEY (id)
);

CREATE TABLE roles (
  id                    serial,
  name                  VARCHAR(80) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE authority (
  id                    serial,
  name                  VARCHAR(80) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE users_roles (
  user_id               bigint NOT NULL,
  role_id               int NOT NULL,
  PRIMARY KEY (user_id, role_id),
  FOREIGN KEY (user_id) REFERENCES users (id),
  FOREIGN KEY (role_id) REFERENCES roles (id)
);

CREATE TABLE roles_authorities (
  role_id                    int NOT NULL,
  authority_id               bigint NOT NULL,
  PRIMARY KEY (role_id, authority_id),
  FOREIGN KEY (role_id)      REFERENCES roles (id),
  FOREIGN KEY (authority_id) REFERENCES authority (id)
);

INSERT INTO roles (name)
VALUES
('ROLE_USER'), ('ROLE_ADMIN'), ('ROLE_SUPERADMIN');

INSERT INTO authority (name)
VALUES
('NOBODY'), ('AWESOME_POSSUM'), ('SUPERIOR');


INSERT INTO users (username, password, email)
VALUES
('user', '$2a$07$iO.vXZHcbSh1kCd9O3s/Uu/w7MGbSB32IN1C821HMTifFiOqLGZpW', 'user@user.com'),
('admin', '$2a$07$Wks689sm8ssGED3mx4cMrOC5CJD5d2fORcimQUX1zo7F4dc3Q1jEK', 'admin@admin.com'),
('super', '$2a$07$Wks689sm8ssGED3mx4cMrOC5CJD5d2fORcimQUX1zo7F4dc3Q1jEK', 'super@admin.com');


INSERT INTO users_roles (user_id, role_id)
VALUES
(1, 1),
(2,1),
(2,2),
(3,3);

INSERT INTO roles_authorities (role_id, authority_id)
VALUES
(1, 1),
(2, 2),
(3,3),
(3,2);