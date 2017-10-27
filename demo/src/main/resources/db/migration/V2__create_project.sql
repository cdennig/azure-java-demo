CREATE TABLE PROJECT(
	ID INT IDENTITY(1,1),
	NAME VARCHAR(255),
	DESCRIPTION VARCHAR(2000),
	ESTIMATED_COST INT,
	STATUS VARCHAR(25),
	PROJECT_LEAD VARCHAR(255),
	CUSTOMER_NAME VARCHAR(255),
);

INSERT INTO PROJECT VALUES(
    'Death Star',
    'Build a space station, the whole galaxy is afraid of!',
    20000000,
    'Active',
    'Emperor Palpatine',
    'The Evil Empire'
);

INSERT INTO PROJECT VALUES(
    'Become a Jedi',
    'Fly to Dagobah and finish the training in order to become a Jedi!',
    500,
    'Paused',
    'Luke Skywalker',
    'The Rebellion'
);