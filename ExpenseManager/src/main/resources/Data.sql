INSERT INTO users(user_id,user_name,opening_amount,email,password,doj) VALUES (1001,'Vignesh',6145,'vignesh.bandla01@gmail.com','password',CURRENT_TIMESTAMP());
INSERT INTO users(user_id,user_name,opening_amount,email,password,doj) VALUES (1002,'Vignesh bandla',6145,'vignesh6145@gmail.com','password',CURRENT_TIMESTAMP());
INSERT INTO users(user_id,user_name,opening_amount,email,password,doj) VALUES (1003,'Vignesh Bandla VIT-AP',6145,'vignesh.19bce7085@vitap.ac.in@gmail.com','password',CURRENT_TIMESTAMP());

INSERT INTO expense(id,description,user_user_id,amount) VALUES(1001,'Vijayawada Petrol',1001,1000);
INSERT INTO expense(id,description,user_user_id,amount) VALUES(1002,'Hyderabad Petrol',1001,2000);
INSERT INTO expense(id,description,user_user_id,amount) VALUES(1003,'Chennai Petrol',1001,3000);