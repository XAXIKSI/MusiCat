-- Initial schema setup for MusiCat application

-- Create Instrument Enum type
CREATE TABLE instruments
(
    name VARCHAR(50) PRIMARY KEY
);

-- Create artists table
CREATE TABLE artists
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(255) NOT NULL,
    instrument VARCHAR(50)  NOT NULL,
    FOREIGN KEY (instrument) REFERENCES instruments (name)
);

-- Create tracks table
CREATE TABLE tracks
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    title         VARCHAR(255) NOT NULL,
    owner         VARCHAR(255) NOT NULL,
    recorded_date DATE         NOT NULL
);

-- Create albums table
CREATE TABLE albums
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    title         VARCHAR(255) NOT NULL,
    owner         VARCHAR(255) NOT NULL,
    released_date DATE         NOT NULL
);

-- Create relationship tables
CREATE TABLE rel_artist_track
(
    artist_id BIGINT NOT NULL,
    track_id  BIGINT NOT NULL,
    PRIMARY KEY (artist_id, track_id),
    FOREIGN KEY (artist_id) REFERENCES artists (id),
    FOREIGN KEY (track_id) REFERENCES tracks (id)
);

CREATE TABLE rel_track_album
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    track_id     BIGINT NOT NULL,
    album_id     BIGINT NOT NULL,
    track_number INT    NOT NULL DEFAULT 1,
    disc_number  INT    NOT NULL DEFAULT 1,
    FOREIGN KEY (track_id) REFERENCES tracks (id),
    FOREIGN KEY (album_id) REFERENCES albums (id)
);