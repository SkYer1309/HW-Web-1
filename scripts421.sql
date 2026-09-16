-- 1. Имя студента должно быть уникальным и не NULL
ALTER TABLE student ALTER COLUMN name SET NOT NULL;
ALTER TABLE student ADD CONSTRAINT student_name_unique UNIQUE (name);

-- 2. Возраст студента не может быть меньше 16 лет
ALTER TABLE student ADD CONSTRAINT student_age_check CHECK (age >= 16);

-- 3. При создании студента без возраста ему автоматически должно присваиваться 20 лет
ALTER TABLE student ALTER COLUMN age SET DEFAULT 20;

-- 4. Пара "название" - "цвет факультета" должна быть уникальной
ALTER TABLE faculty ADD CONSTRAINT faculty_name_color_unique UNIQUE (name, color);