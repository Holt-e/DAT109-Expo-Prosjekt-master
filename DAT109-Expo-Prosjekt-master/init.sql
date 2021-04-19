drop schema expo cascade;

CREATE SCHEMA Expo;
SET search_path TO Expo;

CREATE TABLE Expos
(
  expoId SERIAL PRIMARY KEY,
  expoName VARCHAR UNIQUE,
  startTime TIMESTAMP,
  endTime TIMESTAMP
);

CREATE TABLE Stands
(
  standId SERIAL PRIMARY KEY,
  standName VARCHAR,
  expoId INTEGER
);

CREATE TABLE Votes
(
	voteId SERIAL PRIMARY KEY,
	score INTEGER,
  userid INTEGER,
	standId INTEGER
);

CREATE TABLE Users
(
  userId SERIAL PRIMARY KEY,
	username VARCHAR UNIQUE NOT NULL,
	pin VARCHAR NOT NULL,
	isJury BOOLEAN,
	isAdmin BOOLEAN,
	isVerified BOOLEAN
);

ALTER TABLE Stands
ADD CONSTRAINT standExpoIdFK FOREIGN KEY (expoid) REFERENCES expos (expoid);

ALTER TABLE Votes
ADD CONSTRAINT voteStandFK FOREIGN KEY (standid) REFERENCES stands (standid);

ALTER TABLE Votes
ADD CONSTRAINT voteUserFK FOREIGN KEY (userid) REFERENCES users (userid);