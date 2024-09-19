#Entrar al contenedor
docker exec -it guide-vault sh

#Export de variables
export VAULT_TOKEN="00000000-0000-0000-0000-000000000000"
export VAULT_ADDR="http://127.0.0.1:8200"

#Crear las variables en vault
vault kv put secret/demo-bank-vault spring.datasource.url=jdbc:postgresql://127.0.0.1:5432/bank spring.jpa.properties.hibernate.default_schema=public spring.jpa.open-in-view=false spring.datasource.username=postgres spring.datasource.password=postgres