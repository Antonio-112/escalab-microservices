--------------------------------------------------------------------

INSERT INTO users (username, password, enabled)
  values ('user',
    '$2a$05$.H4C.rxj6UONnXmLqSl81OI7YMC32i9nnnzBRrGgzUpiiqDid.3pG',
    1);
    
----pass:12345
INSERT INTO users (username, password, enabled)
  values ('admin',
    '$2a$05$onnSc6sZZLQZmgo6BCmMm.bgiap3KZKMB9kKJVJazyzdE4402x80e',
    1);
    
---------------------------------------------------------------------
 
INSERT INTO authorities (username, authority)
  values ('user', 'ROLE_USER');
  
INSERT INTO authorities (username, authority)
 values ('admin', 'ROLE_ADMIN');
 
 INSERT INTO authorities (username, authority)
 values ('admin', 'ROLE_USER'); 
 
 
 --------------------------------------------------------------------
