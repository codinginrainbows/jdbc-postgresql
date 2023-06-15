DROP DATABASE IF EXISTS hospital;
CREATE DATABASE hospital;

\c nome_do_BANCO;
-- psql -U postgres
-- criar -> \i nome_do_ARQUIVO.sql;
-- conectar -> \c nome_do_BANCO;
-- sair -> \q

CREATE TABLE cargo (
    codigo serial primary key,
    nome text
);

INSERT INTO cargo (nome) VALUES
('Clinico Geral'),
('Cardiologista'),
('Dermatologista');

CREATE TABLE funcionario (
    codigo serial primary key,
    nome text,
    email text NOT NULL,
    senha text NOT NULL,
    codigo_cargo integer references cargo (codigo)
);

INSERT INTO funcionario (nome, email, senha, codigo_cargo) VALUES
('Dr. Fulano','fulano@gmail.com', '123', 1),
('Dr. Ciclano', 'ciclano@gmail.com', '234', 1),
('Dr. Beltrano', 'beltrano@gmail.com', '345', 2),
('Dr. Alguem', 'alguem@gmail.com', '456', 3);

CREATE TABLE paciente (
    codigo serial primary key,
    nome text NOT NULL,
    endereco text
);

INSERT INTO paciente (nome, endereco) VALUES
('Joao', 'Silva Paes, 12'),
('Maria', 'Bacelar, 482'),
('Natalia', 'Av. Portugal, 1009'),
('Joelinton', 'Tiradentes, 250');

CREATE TABLE setor (
    codigo serial primary key,
    descricao text
);

INSERT INTO setor (descricao) VALUES
('Cardiologia'),
('Pediatria'),
('Dermatologia');

CREATE TABLE atendimento (
    data_hora timestamp,
    diagnostico text,
    codigo_funcionario integer references funcionario (codigo) ON DELETE CASCADE,
    codigo_paciente integer references paciente (codigo) ON DELETE CASCADE,
    primary key (codigo_funcionario, codigo_paciente)
);

INSERT INTO atendimento (data_hora, diagnostico, codigo_paciente, codigo_funcionario) VALUES
('2018-04-04 14:30:00', 'Cancer', 1, 1),
('2023-04-04 08:45:00', 'Tumor', 2, 2),
('2009-10-12 21:15:00', 'Hemorroida', 3, 3),
('2023-05-07 08:45:00', 'H1N1', 4, 4);

CREATE TABLE setor_funcionario (
    codigo_funcionario integer references funcionario (codigo) ON DELETE CASCADE,
    codigo_setor integer references setor (codigo) ON DELETE CASCADE,
    primary key (codigo_funcionario, codigo_setor)
);

INSERT INTO setor_funcionario (codigo_funcionario, codigo_setor) VALUES
(1, 1),
(1, 2),
(1, 3),
(2, 2),
(3, 3),
(4, 2);


-- 1) SELECT funcionario.nome as medico, cargo.nome as especialidade, STRING_AGG(setor.descricao, ', ') AS setores FROM funcionario INNER JOIN cargo ON cargo.codigo = funcionario.codigo_cargo INNER JOIN setor_funcionario ON funcionario.codigo = setor_funcionario.codigo_funcionario INNER JOIN setor ON setor_funcionario.codigo_setor = setor.codigo GROUP BY funcionario.nome, cargo.nome ORDER BY funcionario.nome DESC;

-- 2) SELECT atendimento.data_hora as agendamento, funcionario.nome as medico, paciente.nome as paciente FROM atendimento INNER JOIN funcionario ON funcionario.codigo = atendimento.codigo_funcionario INNER JOIN paciente ON paciente.codigo = atendimento.codigo_paciente WHERE extract(month from data_hora) = 4 and extract(year from data_hora) = 2023;