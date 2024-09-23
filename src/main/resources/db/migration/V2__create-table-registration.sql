CREATE TABLE reservation (
    id VARCHAR(36) NOT NULL PRIMARY KEY, -- UUID para o campo id
    id_room VARCHAR(36) NOT NULL, -- Inicialmente, apenas como coluna
    id_user VARCHAR(36) NOT NULL, -- Inicialmente, apenas como coluna
    check_in DATE NOT NULL, -- Data de check-in
    check_out DATE NOT NULL, -- Data de check-out
    reservationStatus VARCHAR(255) NOT NULL -- Enum ou string para o status da reserva
);



