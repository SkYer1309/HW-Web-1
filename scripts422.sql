-- Таблица машин (создаем первой, так как на нее ссылается person)
CREATE TABLE car (
    id SERIAL PRIMARY KEY,
    brand VARCHAR(100) NOT NULL,
    model VARCHAR(100) NOT NULL,
    price DECIMAL(10, 2)
);

-- Таблица людей
CREATE TABLE person (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INTEGER,
    has_license BOOLEAN DEFAULT FALSE,
    car_id INTEGER REFERENCES car(id)
);