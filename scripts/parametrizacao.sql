--COMUM
DELETE FROM PARAMETRIZACAO WHERE DC_PARAMETRO IN (
'grupo_dn_master',
'grupo_dn_usuario',
'ip_ldap',
'login_dn',
'passwod_ldap',
'porta_ldap',
'user_dn_path',
'user_dn_teste',
'user_dn_path_base',
'grupo_dn_master_base',
'ldap_root',
'grupo_dn_usuario_base',
'ldap_host',
'ldap_port',
'ldap_manager_login',
'ldap_manager_password',
'mail_host',
'mail_port',
'mail_username',
'mail_password',
'recaptcha_site_key',
'recaptcha_secret_key'
);

--SOMENTE PROD
Insert into PARAMETRIZACAO (CD_PARAMETRO,DC_PARAMETRO,VL_PARAMETRO,ID_SITUACAO) values ('53','user_dn_path_base','ou=usuarios','1');
Insert into PARAMETRIZACAO (CD_PARAMETRO,DC_PARAMETRO,VL_PARAMETRO,ID_SITUACAO) values ('54','grupo_dn_master_base','cn=USUARIO_MASTER,ou=grupos','1');
Insert into PARAMETRIZACAO (CD_PARAMETRO,DC_PARAMETRO,VL_PARAMETRO,ID_SITUACAO) values ('55','ldap_root','dc=pgmch','1');
Insert into PARAMETRIZACAO (CD_PARAMETRO,DC_PARAMETRO,VL_PARAMETRO,ID_SITUACAO) values ('56','grupo_dn_usuario_base','cn=USUARIO,ou=grupos','1');
Insert into PARAMETRIZACAO (CD_PARAMETRO,DC_PARAMETRO,VL_PARAMETRO,ID_SITUACAO) values ('57','ldap_host','10.100.103.150','1');
Insert into PARAMETRIZACAO (CD_PARAMETRO,DC_PARAMETRO,VL_PARAMETRO,ID_SITUACAO) values ('58','ldap_port','389','1');
Insert into PARAMETRIZACAO (CD_PARAMETRO,DC_PARAMETRO,VL_PARAMETRO,ID_SITUACAO) values ('59','ldap_manager_login','cn=admin,dc=pgmch','1');
Insert into PARAMETRIZACAO (CD_PARAMETRO,DC_PARAMETRO,VL_PARAMETRO,ID_SITUACAO) values ('60','ldap_manager_password','1pgmctemporario55','1');
Insert into PARAMETRIZACAO (CD_PARAMETRO,DC_PARAMETRO,VL_PARAMETRO,ID_SITUACAO) values ('61','mail_host','mta.portabilidade.ctech.local','1');
Insert into PARAMETRIZACAO (CD_PARAMETRO,DC_PARAMETRO,VL_PARAMETRO,ID_SITUACAO) values ('62','mail_port','25','1');
Insert into PARAMETRIZACAO (CD_PARAMETRO,DC_PARAMETRO,VL_PARAMETRO,ID_SITUACAO) values ('63','mail_username','','1');
Insert into PARAMETRIZACAO (CD_PARAMETRO,DC_PARAMETRO,VL_PARAMETRO,ID_SITUACAO) values ('64','mail_password','','1');
Insert into PARAMETRIZACAO (CD_PARAMETRO,DC_PARAMETRO,VL_PARAMETRO,ID_SITUACAO) values ('65','recaptcha_site_key','6LfgZz8UAAAAAIziASwL_Gcb0R5GcOCOm6IgYv45','1');
Insert into PARAMETRIZACAO (CD_PARAMETRO,DC_PARAMETRO,VL_PARAMETRO,ID_SITUACAO) values ('66','recaptcha_secret_key','6LfgZz8UAAAAAC0rw4DREGCIvPnHDjN33Ck2jXP2','1');

--SOMENTE AMBIENTES FICTICIOS - **ficar atento ao ou=cleartech (adapte para cada ambiente)
Insert into PARAMETRIZACAO (CD_PARAMETRO,DC_PARAMETRO,VL_PARAMETRO,ID_SITUACAO) values ('53','user_dn_path_base','ou=usuarios,ou=cleartech','1');
Insert into PARAMETRIZACAO (CD_PARAMETRO,DC_PARAMETRO,VL_PARAMETRO,ID_SITUACAO) values ('54','grupo_dn_master_base','cn=USUARIO_MASTER,ou=grupos,ou=cleartech','1');
Insert into PARAMETRIZACAO (CD_PARAMETRO,DC_PARAMETRO,VL_PARAMETRO,ID_SITUACAO) values ('55','ldap_root','dc=pgmchom','1');
Insert into PARAMETRIZACAO (CD_PARAMETRO,DC_PARAMETRO,VL_PARAMETRO,ID_SITUACAO) values ('56','grupo_dn_usuario_base','cn=USUARIO,ou=grupos,ou=cleartech','1');
Insert into PARAMETRIZACAO (CD_PARAMETRO,DC_PARAMETRO,VL_PARAMETRO,ID_SITUACAO) values ('57','ldap_host','10.100.103.150','1');
Insert into PARAMETRIZACAO (CD_PARAMETRO,DC_PARAMETRO,VL_PARAMETRO,ID_SITUACAO) values ('58','ldap_port','389','1');
Insert into PARAMETRIZACAO (CD_PARAMETRO,DC_PARAMETRO,VL_PARAMETRO,ID_SITUACAO) values ('59','ldap_manager_login','cn=admin,dc=pgmchom','1');
Insert into PARAMETRIZACAO (CD_PARAMETRO,DC_PARAMETRO,VL_PARAMETRO,ID_SITUACAO) values ('60','ldap_manager_password','1pgmchom55','1');
Insert into PARAMETRIZACAO (CD_PARAMETRO,DC_PARAMETRO,VL_PARAMETRO,ID_SITUACAO) values ('61','mail_host','mta.portabilidade.ctech.local','1');
Insert into PARAMETRIZACAO (CD_PARAMETRO,DC_PARAMETRO,VL_PARAMETRO,ID_SITUACAO) values ('62','mail_port','25','1');
Insert into PARAMETRIZACAO (CD_PARAMETRO,DC_PARAMETRO,VL_PARAMETRO,ID_SITUACAO) values ('63','mail_username','','1');
Insert into PARAMETRIZACAO (CD_PARAMETRO,DC_PARAMETRO,VL_PARAMETRO,ID_SITUACAO) values ('64','mail_password','','1');
Insert into PARAMETRIZACAO (CD_PARAMETRO,DC_PARAMETRO,VL_PARAMETRO,ID_SITUACAO) values ('65','recaptcha_site_key','6LfgZz8UAAAAAIziASwL_Gcb0R5GcOCOm6IgYv45','1');
Insert into PARAMETRIZACAO (CD_PARAMETRO,DC_PARAMETRO,VL_PARAMETRO,ID_SITUACAO) values ('66','recaptcha_secret_key','6LfgZz8UAAAAAC0rw4DREGCIvPnHDjN33Ck2jXP2','1');


--SOMENTE LOCALHOST
Insert into PARAMETRIZACAO (CD_PARAMETRO,DC_PARAMETRO,VL_PARAMETRO,ID_SITUACAO) values ('53','user_dn_path_base','ou=usuarios,ou=cleartech','1');
Insert into PARAMETRIZACAO (CD_PARAMETRO,DC_PARAMETRO,VL_PARAMETRO,ID_SITUACAO) values ('54','grupo_dn_master_base','cn=USUARIO_MASTER,ou=grupos,ou=cleartech','1');
Insert into PARAMETRIZACAO (CD_PARAMETRO,DC_PARAMETRO,VL_PARAMETRO,ID_SITUACAO) values ('55','ldap_root','dc=pgmchom','1');
Insert into PARAMETRIZACAO (CD_PARAMETRO,DC_PARAMETRO,VL_PARAMETRO,ID_SITUACAO) values ('56','grupo_dn_usuario_base','cn=USUARIO,ou=grupos,ou=cleartech','1');
Insert into PARAMETRIZACAO (CD_PARAMETRO,DC_PARAMETRO,VL_PARAMETRO,ID_SITUACAO) values ('57','ldap_host','10.100.103.150','1');
Insert into PARAMETRIZACAO (CD_PARAMETRO,DC_PARAMETRO,VL_PARAMETRO,ID_SITUACAO) values ('58','ldap_port','389','1');
Insert into PARAMETRIZACAO (CD_PARAMETRO,DC_PARAMETRO,VL_PARAMETRO,ID_SITUACAO) values ('59','ldap_manager_login','cn=admin,dc=pgmchom','1');
Insert into PARAMETRIZACAO (CD_PARAMETRO,DC_PARAMETRO,VL_PARAMETRO,ID_SITUACAO) values ('60','ldap_manager_password','1pgmchom55','1');
Insert into PARAMETRIZACAO (CD_PARAMETRO,DC_PARAMETRO,VL_PARAMETRO,ID_SITUACAO) values ('61','mail_host','smtp.cleartech.com.br','1');
Insert into PARAMETRIZACAO (CD_PARAMETRO,DC_PARAMETRO,VL_PARAMETRO,ID_SITUACAO) values ('62','mail_port','25','1');
Insert into PARAMETRIZACAO (CD_PARAMETRO,DC_PARAMETRO,VL_PARAMETRO,ID_SITUACAO) values ('63','mail_username','svc_no_reply','1');
Insert into PARAMETRIZACAO (CD_PARAMETRO,DC_PARAMETRO,VL_PARAMETRO,ID_SITUACAO) values ('64','mail_password','CTECH@noreply','1');
Insert into PARAMETRIZACAO (CD_PARAMETRO,DC_PARAMETRO,VL_PARAMETRO,ID_SITUACAO) values ('65','recaptcha_site_key','6LfgZz8UAAAAAIziASwL_Gcb0R5GcOCOm6IgYv45','1');
Insert into PARAMETRIZACAO (CD_PARAMETRO,DC_PARAMETRO,VL_PARAMETRO,ID_SITUACAO) values ('66','recaptcha_secret_key','6LfgZz8UAAAAAC0rw4DREGCIvPnHDjN33Ck2jXP2','1');