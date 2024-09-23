ALTER TABLE reservation
    ADD CONSTRAINT fk_room FOREIGN KEY (id_room) REFERENCES room(id),
    ADD CONSTRAINT fk_user FOREIGN KEY (id_user) REFERENCES users(id);