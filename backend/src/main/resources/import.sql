INSERT INTO tb_user (first_name, last_name, email, password) VALUES ('Vinicius', 'Miranda', 'vinicius@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_admin (user_id) VALUES (1);

INSERT INTO tb_role (authority) VALUES ('ROLE_CUSTOMER');
INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 2);

INSERT INTO tb_developer (name) VALUES ('Rocksteady Studios');
INSERT INTO tb_developer (name) VALUES ('NetherRealm Studios, QLOC, Shiver');
INSERT INTO tb_developer (name) VALUES ('Rockstar Games');
INSERT INTO tb_developer (name) VALUES ('Monolith Productions');
INSERT INTO tb_developer (name) VALUES ('Red Hook Studios');
INSERT INTO tb_developer (name) VALUES ('CAPCOM Co., Ltd.');
INSERT INTO tb_developer (name) VALUES ('Bandai Namco Studios Inc.');
INSERT INTO tb_developer (name) VALUES ('Sonic team');
INSERT INTO tb_developer (name) VALUES ('Asobo Studio');
INSERT INTO tb_developer (name) VALUES ('Demonware');
INSERT INTO tb_developer (name) VALUES ('Atlus');
INSERT INTO tb_developer (name) VALUES ('Tango Gameworks');
INSERT INTO tb_developer (name) VALUES ('Electronic Arts');
INSERT INTO tb_developer (name) VALUES ('Playground Games');
INSERT INTO tb_developer (name) VALUES ('Team Cherry');
INSERT INTO tb_developer (name) VALUES ('Kinetic Games');
INSERT INTO tb_developer (name) VALUES ('Team Cherry');
INSERT INTO tb_developer (name) VALUES ('ConcernedApe');
INSERT INTO tb_developer (name) VALUES ('Naughty Dog');

INSERT INTO tb_distributor (name) VALUES ('Warner Bros. Games');
INSERT INTO tb_distributor (name) VALUES ('Rockstar Games');
INSERT INTO tb_distributor (name) VALUES ('Red Hook Studios');
INSERT INTO tb_distributor (name) VALUES ('CAPCOM Co., Ltd.');
INSERT INTO tb_distributor (name) VALUES ('Bandai Namco Entertainment');
INSERT INTO tb_distributor (name) VALUES ('SEGA');
INSERT INTO tb_distributor (name) VALUES ('Focus Entertainment');
INSERT INTO tb_distributor (name) VALUES ('Activision');
INSERT INTO tb_distributor (name) VALUES ('Bethesda Softworks');
INSERT INTO tb_distributor (name) VALUES ('Electronic Arts');
INSERT INTO tb_distributor (name) VALUES ('Xbox Game Studios');
INSERT INTO tb_distributor (name) VALUES ('Team Cherry');
INSERT INTO tb_distributor (name) VALUES ('ConcernedApe');
INSERT INTO tb_distributor (name) VALUES ('Kinetic Games');
INSERT INTO tb_distributor (name) VALUES ('PlayStation PC LLC');

INSERT INTO tb_product (name, quantity, description, price, developer_id, distributor_id, launch_date, img_url, created_at) VALUES ('Batman Arkham Collection', 10, 'O aclamado pela crítica Batman: Arkham Asylum retorna com uma edição remasterizada do Jogo do Ano, com 4 Mapas de Desafio extras.', 199.99, 1, 1, TIMESTAMP WITH TIME ZONE '2018-11-28T00:00:00.00Z', 'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/1-big.jpg', NOW());
INSERT INTO tb_product (name, quantity, description, price, developer_id, distributor_id, launch_date, img_url, created_at) VALUES ('Mortal Kombat 11', 7, 'Todas as variações de customização de personagens lhe dão liberdade total para personalizar os lutadores e torná-los únicos! MK 11 faz você mergulhar em crânios rachados e uma grande quantidade de partes do corpo do seu oponente voando; com lutadores clássicos, novos e antigos, a incrível cinemática do modo história de Mortal Kombat continua a contar a saga épica de 25 anos atrás.', 159.99, 2, 1, TIMESTAMP WITH TIME ZONE '2019-04-23T00:00:00Z', 'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/2-big.jpg', NOW());
INSERT INTO tb_product (name, quantity, description, price, developer_id, distributor_id, launch_date, img_url, created_at) VALUES ('Red Dead Redemption 2: Ultimate Edition', 2, 'Vencedor de mais de 175 prêmios de Jogo do Ano e ganhador de mais de 250 pontuações perfeitas, Red Dead Redemption 2 é o conto épico do fora-da-lei Arthur Morgan e da infame gangue Van der Linde, na corrida pela América no alvorecer da era moderna. Também inclui o acesso ao mundo vivo compartilhado do Red Dead Online.', 349.90, 3, 2, TIMESTAMP WITH TIME ZONE '2019-12-05T00:00:00Z', 'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/3-big.jpg', NOW());
INSERT INTO tb_product (name, quantity, description, price, developer_id, distributor_id, launch_date, img_url, created_at) VALUES ('Middle-earth: Shadow of Mordor - Game of the Year Edition', 12, 'A versão Game of the Year Edition contém o jogo base Middle-earth: Shadow of Mordor e todas as suas DLCs!', 89.99, 4, 1, TIMESTAMP WITH TIME ZONE '2018-11-28T00:00:00.00Z', 'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/1-big.jpg', NOW());
INSERT INTO tb_product (name, quantity, description, price, developer_id, distributor_id, launch_date, img_url, created_at) VALUES ('Darkest Dungeon', 13, 'Darkest Dungeon é um jogo gótico desafiante em turnos de RPG sobre os estresses psicológicos vivenciados em aventuras.', 45.99, 5, 3, TIMESTAMP WITH TIME ZONE '2018-11-28T00:00:00.00Z', 'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/1-big.jpg', NOW());
INSERT INTO tb_product (name, quantity, description, price, developer_id, distributor_id, launch_date, img_url, created_at) VALUES ('Resident Evil 4', 17, 'Seis anos se passaram desde o desastre biológico em Raccoon City.O agente Leon S. Kennedy, um dos sobreviventes do incidente, foi enviado par resgatar a filha raptada do presidente.', 199.99, 6, 4, TIMESTAMP WITH TIME ZONE '2018-11-28T00:00:00.00Z', 'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/1-big.jpg', NOW());
INSERT INTO tb_product (name, quantity, description, price, developer_id, distributor_id, launch_date, img_url, created_at) VALUES ('Monster Hunter Rise - Deluxe Edition', 6, 'Monster Hunter Rise - Deluxe Edition Inclui Monster Hunter Rise e o conteúdo do Deluxe Kit em um pacote!', 168.25, 6, 4, TIMESTAMP WITH TIME ZONE '2018-11-28T00:00:00.00Z', 'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/1-big.jpg', NOW());
INSERT INTO tb_product (name, quantity, description, price, developer_id, distributor_id, launch_date, img_url, created_at) VALUES ('Tales of Arise', 5, '300 anos de tirania. Uma máscara misteriosa. Dor e memórias perdidas. Empunhe a Espada Flamejante e se una a uma jovem intocável para enfrentar os opressores. Viva uma história de libertação com personagens caprichados nos gráficos de nova geração!', 249.99, 7, 5, TIMESTAMP WITH TIME ZONE '2018-11-28T00:00:00.00Z', 'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/1-big.jpg', NOW());
INSERT INTO tb_product (name, quantity, description, price, developer_id, distributor_id, launch_date, img_url, created_at) VALUES ('Sonic Frontiers', 10, 'Uma experiência inédita de Sonic!', 299.99, 8, 6, TIMESTAMP WITH TIME ZONE '2018-11-28T00:00:00.00Z', 'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/1-big.jpg', NOW());
INSERT INTO tb_product (name, quantity, description, price, developer_id, distributor_id, launch_date, img_url, created_at) VALUES ('A Plague Tale: Requiem', 17, 'Embarque em uma comovente jornada por um cativante mundo brutal e descubra o preço pago para salvar aqueles que você ama nesta desesperada luta pela sobrevivência.', 169.90, 9, 7, TIMESTAMP WITH TIME ZONE '2018-11-28T00:00:00.00Z', 'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/1-big.jpg', NOW());
INSERT INTO tb_product (name, quantity, description, price, developer_id, distributor_id, launch_date, img_url, created_at) VALUES ('Street Fighter V', 10, 'Experimente os intensos combates um contra um de Street Fighter® V! Escolha entre 16 personagens icônicos, cada um com sua história pessoa e desafios de treinamento únicos, e lute contra amigos, online ou offline.', 66.90, 6, 4, TIMESTAMP WITH TIME ZONE '2018-11-28T00:00:00.00Z', 'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/1-big.jpg', NOW());
INSERT INTO tb_product (name, quantity, description, price, developer_id, distributor_id, launch_date, img_url, created_at) VALUES ('Call of Duty®: Modern Warfare® II', 40, 'O Call of Duty®: Modern Warfare® II coloca os jogadores dentro de um conflito global sem precedentes que conta com o retorno dos Operadores icônicos da Força-Tarefa 141.', 299.90, 10, 8, TIMESTAMP WITH TIME ZONE '2018-11-28T00:00:00.00Z', 'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/1-big.jpg', NOW());
INSERT INTO tb_product (name, quantity, description, price, developer_id, distributor_id, launch_date, img_url, created_at) VALUES ('Persona 5 Royal', 32, 'Persona 5 é um jogo eletrônico de RPG desenvolvido pela Atlus. O jogo é cronologicamente a sexta edição da série Persona, que faz parte principalmente da franquia Megami Tensei.', 299.95, 11, 6, TIMESTAMP WITH TIME ZONE '2018-11-28T00:00:00.00Z', 'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/1-big.jpg', NOW());
INSERT INTO tb_product (name, quantity, description, price, developer_id, distributor_id, launch_date, img_url, created_at) VALUES ('The Evil Within 2', 10, 'Do mestre Shinji Mikami, The Evil Within 2 é a última evolução em terror de sobrevivência. O detetive Sebastian Castellanos perdeu tudo, mas quando ganha uma chance de salvar sua filha, ele deve descer mais uma vez ao mundo tenebroso de STEM.', 155.00, 12, 9, TIMESTAMP WITH TIME ZONE '2018-11-28T00:00:00.00Z', 'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/1-big.jpg', NOW());
INSERT INTO tb_product (name, quantity, description, price, developer_id, distributor_id, launch_date, img_url, created_at) VALUES ('EA SPORTS™ FIFA 23', 12, 'EA SPORTS™ FIFA 23 traz o jogo de todo mundo aos gramados com a tecnologia HyperMotion2 proporcionando jogabilidade com ainda mais realismo, a FIFA World Cup™', 299.90, 13, 10, TIMESTAMP WITH TIME ZONE '2018-11-28T00:00:00.00Z', 'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/1-big.jpg', NOW());
INSERT INTO tb_product (name, quantity, description, price, developer_id, distributor_id, launch_date, img_url, created_at) VALUES ('Forza Horizon 5', 10, 'Sua maior aventura Horizon te espera! Lidere impressionantes expedições pelo mundo aberto vibrante e em constante evolução nas terras mexicanas. Participe de corridas divertidas e sem limites enquanto pilota centenas dos melhores carros do mundo.', 249.90, 14, 11, TIMESTAMP WITH TIME ZONE '2018-11-28T00:00:00.00Z', 'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/1-big.jpg', NOW());
INSERT INTO tb_product (name, quantity, description, price, developer_id, distributor_id, launch_date, img_url, created_at) VALUES ('Devil May Cry 5', 10, 'O melhor caçador de demônios está de volta com estilo, no jogo que os fãs de ação estavam esperando.', 99.99, 6, 4, TIMESTAMP WITH TIME ZONE '2018-11-28T00:00:00.00Z', 'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/1-big.jpg', NOW());
INSERT INTO tb_product (name, quantity, description, price, developer_id, distributor_id, launch_date, img_url, created_at) VALUES ('Hollow Knight', 10, 'Abaixo da cidade moribunda de Dirtmouth jaz um reino antigo e arruinado. Muitos são atraídos para o subterrâneo em busca de riquezas, glórias ou respostas para antigos segredos.', 27.99, 15, 12, TIMESTAMP WITH TIME ZONE '2018-11-28T00:00:00.00Z', 'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/1-big.jpg', NOW());
INSERT INTO tb_product (name, quantity, description, price, developer_id, distributor_id, launch_date, img_url, created_at) VALUES ('PHASMOPHOBIA', 10, 'Phasmophobia is a 4 player online co-op psychological horror where you and your team members of paranormal investigators will enter haunted locations filled with paranormal activity and gather as much evidence of the paranormal as you can.', 27.99, 16, 13, TIMESTAMP WITH TIME ZONE '2018-11-28T00:00:00.00Z', 'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/1-big.jpg', NOW());
INSERT INTO tb_product (name, quantity, description, price, developer_id, distributor_id, launch_date, img_url, created_at) VALUES ('Stardew Valley', 10, 'Você herdou a antiga fazenda do seu avô, em Stardew Valley. Com ferramentas de segunda-mão e algumas moedas, você parte para dar início a sua nova vida. Será que você vai aprender a viver da terra, a transformar esse matagal em um próspero lar?', 24.99, 17, 14, TIMESTAMP WITH TIME ZONE '2018-11-28T00:00:00.00Z', 'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/1-big.jpg', NOW());
INSERT INTO tb_product (name, quantity, description, price, developer_id, distributor_id, launch_date, img_url, created_at) VALUES ('Plants vs. Zombies™ Garden Warfare 2: Edição Deluxe', 10, 'Carregue as Disparervilhas e prepare-se para o jogo de tiro mais doido e divertido do universo: Plants vs. Zombies Garden Warfare 2.', 89.99, 18, 10, TIMESTAMP WITH TIME ZONE '2018-11-28T00:00:00.00Z', 'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/1-big.jpg', NOW());
INSERT INTO tb_product (name, quantity, description, price, developer_id, distributor_id, launch_date, img_url, created_at) VALUES ('UNCHARTED™: Coleção Legado dos Ladrões', 10, 'Pronto para buscar sua fortuna?', 199.90, 19, 15, TIMESTAMP WITH TIME ZONE '2018-11-28T00:00:00.00Z', 'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/1-big.jpg', NOW());

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
INSERT INTO tb_product_category (product_id, category_id) VALUES (4, 1);
INSERT INTO tb_product_category (product_id, category_id) VALUES (4, 6);
INSERT INTO tb_product_category (product_id, category_id) VALUES (4, 9);
INSERT INTO tb_product_category (product_id, category_id) VALUES (5, 5);
INSERT INTO tb_product_category (product_id, category_id) VALUES (5, 3);
INSERT INTO tb_product_category (product_id, category_id) VALUES (6, 1);
INSERT INTO tb_product_category (product_id, category_id) VALUES (6, 3);
INSERT INTO tb_product_category (product_id, category_id) VALUES (6, 11);
INSERT INTO tb_product_category (product_id, category_id) VALUES (7, 1);
INSERT INTO tb_product_category (product_id, category_id) VALUES (7, 2);
INSERT INTO tb_product_category (product_id, category_id) VALUES (8, 1);
INSERT INTO tb_product_category (product_id, category_id) VALUES (8, 2);
INSERT INTO tb_product_category (product_id, category_id) VALUES (8, 4);
INSERT INTO tb_product_category (product_id, category_id) VALUES (8, 5);
INSERT INTO tb_product_category (product_id, category_id) VALUES (9, 1);
INSERT INTO tb_product_category (product_id, category_id) VALUES (9, 2);
INSERT INTO tb_product_category (product_id, category_id) VALUES (9, 4);
INSERT INTO tb_product_category (product_id, category_id) VALUES (9, 6);
INSERT INTO tb_product_category (product_id, category_id) VALUES (10, 3);
INSERT INTO tb_product_category (product_id, category_id) VALUES (10, 1);
INSERT INTO tb_product_category (product_id, category_id) VALUES (10, 4);
INSERT INTO tb_product_category (product_id, category_id) VALUES (11, 1);
INSERT INTO tb_product_category (product_id, category_id) VALUES (11, 9);
INSERT INTO tb_product_category (product_id, category_id) VALUES (12, 1);
INSERT INTO tb_product_category (product_id, category_id) VALUES (12, 11);
INSERT INTO tb_product_category (product_id, category_id) VALUES (13, 2);
INSERT INTO tb_product_category (product_id, category_id) VALUES (13, 4);
INSERT INTO tb_product_category (product_id, category_id) VALUES (13, 10);
INSERT INTO tb_product_category (product_id, category_id) VALUES (14, 7);
INSERT INTO tb_product_category (product_id, category_id) VALUES (14, 10);
INSERT INTO tb_product_category (product_id, category_id) VALUES (15, 4);
INSERT INTO tb_product_category (product_id, category_id) VALUES (15, 6);
INSERT INTO tb_product_category (product_id, category_id) VALUES (15, 10);
INSERT INTO tb_product_category (product_id, category_id) VALUES (16, 4);
INSERT INTO tb_product_category (product_id, category_id) VALUES (16, 6);
INSERT INTO tb_product_category (product_id, category_id) VALUES (16, 10);
INSERT INTO tb_product_category (product_id, category_id) VALUES (17, 1);
INSERT INTO tb_product_category (product_id, category_id) VALUES (17, 9);
INSERT INTO tb_product_category (product_id, category_id) VALUES (18, 1);
INSERT INTO tb_product_category (product_id, category_id) VALUES (18, 4);
INSERT INTO tb_product_category (product_id, category_id) VALUES (18, 6);
INSERT INTO tb_product_category (product_id, category_id) VALUES (19, 3);
INSERT INTO tb_product_category (product_id, category_id) VALUES (19, 5);
INSERT INTO tb_product_category (product_id, category_id) VALUES (19, 10);
INSERT INTO tb_product_category (product_id, category_id) VALUES (20, 2);
INSERT INTO tb_product_category (product_id, category_id) VALUES (20, 4);
INSERT INTO tb_product_category (product_id, category_id) VALUES (20, 10);
INSERT INTO tb_product_category (product_id, category_id) VALUES (21, 1);
INSERT INTO tb_product_category (product_id, category_id) VALUES (21, 11);
INSERT INTO tb_product_category (product_id, category_id) VALUES (22, 1);
INSERT INTO tb_product_category (product_id, category_id) VALUES (22, 4);
INSERT INTO tb_product_category (product_id, category_id) VALUES (22, 11);

INSERT INTO tb_banner (name, created_at) VALUES ('VISA', NOW());
INSERT INTO tb_banner (name, created_at) VALUES ('MASTERCARD', NOW());
INSERT INTO tb_banner (name, created_at) VALUES ('ELO', NOW());
INSERT INTO tb_banner (name, created_at) VALUES ('AMERICAN EXPRESS', NOW());
INSERT INTO tb_banner (name, created_at) VALUES ('MAESTRO', NOW());