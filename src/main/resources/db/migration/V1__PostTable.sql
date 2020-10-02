CREATE TABLE Post (
    id UUID NOT NULL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description VARCHAR(1000) NOT NULL,
    upVotes INT,
    downVotes INT,
    userId UUID
);