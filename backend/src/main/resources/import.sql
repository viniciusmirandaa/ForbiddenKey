INSERT INTO tb_user (first_name, last_name, email, password) VALUES ('Vinicius', 'Miranda', 'vinicius@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_admin (user_id) VALUES (1);

INSERT INTO tb_role (authority) VALUES ('ROLE_CUSTOMER');
INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 2);

INSERT INTO tb_developer (name) VALUES ('Rocksteady Studios');
INSERT INTO tb_developer (name) VALUES ('NetherRealm Studios, QLOC, Shiver');
INSERT INTO tb_developer (name) VALUES ('Rockstar Games');

INSERT INTO tb_distributor (name) VALUES ('Warner Bros. Games');
INSERT INTO tb_distributor (name) VALUES ('Rockstar Games');

INSERT INTO tb_product (name, description, price, developer_id, distributor_id, launch_date, img_url, created_at) VALUES ('Batman Arkham Collection', 'O aclamado pela crítica Batman: Arkham Asylum retorna com uma edição remasterizada do Jogo do Ano, com 4 Mapas de Desafio extras.', 199.99, 1, 1, TIMESTAMP WITH TIME ZONE '2018-11-28T00:00:00.00Z', 'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/1-big.jpg', NOW());
INSERT INTO tb_product (name, description, price, developer_id, distributor_id, launch_date, img_url, created_at) VALUES ('Mortal Kombat 11', 'Todas as variações de customização de personagens lhe dão liberdade total para personalizar os lutadores e torná-los únicos! MK 11 faz você mergulhar em crânios rachados e uma grande quantidade de partes do corpo do seu oponente voando; com lutadores clássicos, novos e antigos, a incrível cinemática do modo história de Mortal Kombat continua a contar a saga épica de 25 anos atrás.', 159.99, 2, 1, TIMESTAMP WITH TIME ZONE '2019-04-23T00:00:00Z', 'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/2-big.jpg', NOW());
INSERT INTO tb_product (name, description, price, developer_id, distributor_id, launch_date, img_url, created_at) VALUES ('Red Dead Redemption 2: Ultimate Edition', 'Vencedor de mais de 175 prêmios de Jogo do Ano e ganhador de mais de 250 pontuações perfeitas, Red Dead Redemption 2 é o conto épico do fora-da-lei Arthur Morgan e da infame gangue Van der Linde, na corrida pela América no alvorecer da era moderna. Também inclui o acesso ao mundo vivo compartilhado do Red Dead Online.', 349.90, 3, 2, TIMESTAMP WITH TIME ZONE '2019-12-05T00:00:00Z', 'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/3-big.jpg', NOW());

INSERT INTO tb_category (name) VALUES ('AÇÃO');
INSERT INTO tb_category (name) VALUES ('RPG');
INSERT INTO tb_category (name) VALUES ('TERROR');
INSERT INTO tb_category (name) VALUES ('AVENTURA');
INSERT INTO tb_category (name) VALUES ('ESTRATÉGIA');
INSERT INTO tb_category (name) VALUES ('MUNDO ABERTO');
INSERT INTO tb_category (name) VALUES ('ESPORTE');
INSERT INTO tb_category (name) VALUES ('HQ');
INSERT INTO tb_category (name) VALUES ('LUTA');
INSERT INTO tb_category (name) VALUES ('SIMULAÇÃO');
INSERT INTO tb_category (name) VALUES ('TIRO');

INSERT INTO tb_product_category (product_id, category_id) VALUES (1, 1);
INSERT INTO tb_product_category (product_id, category_id) VALUES (1, 4);
INSERT INTO tb_product_category (product_id, category_id) VALUES (1, 5);
INSERT INTO tb_product_category (product_id, category_id) VALUES (1, 6);
INSERT INTO tb_product_category (product_id, category_id) VALUES (2, 7);
INSERT INTO tb_product_category (product_id, category_id) VALUES (2, 8);
INSERT INTO tb_product_category (product_id, category_id) VALUES (2, 9);
INSERT INTO tb_product_category (product_id, category_id) VALUES (2, 10);
INSERT INTO tb_product_category (product_id, category_id) VALUES (3, 1);
INSERT INTO tb_product_category (product_id, category_id) VALUES (3, 4);
INSERT INTO tb_product_category (product_id, category_id) VALUES (3, 6);
INSERT INTO tb_product_category (product_id, category_id) VALUES (3, 11);