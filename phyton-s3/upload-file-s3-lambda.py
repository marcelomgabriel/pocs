import json
import boto3

def lambda_handler(event, context):
 
 # Variaveis
 path_File = "/tmp/transacoes.txt"
 fileName = "transacoes.txt"
 nameS3 = "transacoes-enviadas"
 
 # Cria arquivo
arquivo = open("/tmp/transacoes.txt", 'w')
arquivo.write('{ "nsu": 1,  "cartao":  "1234567890123456"  ,   "valor": "10.00",  "data": "10102021"  } \n')
arquivo.write('{ "nsu": 2,  "cartao":  "2234567890123456"  ,   "valor": "20.00",  "data": "20102021"  } \n')
arquivo.write('{ "nsu": 3,  "cartao":  "3234567890123456"  ,   "valor": "30.00",  "data": "30102021"  } \n')
arquivo.close()


#"Upload S3"
s3 = boto3.client('s3')
with open ("/tmp/transacoes.txt", "rb")  as f:
    s3.upload_fileobj(f, "transacoes-enviadas", "transacoes.txt")
    
print ('processamento efetuado com sucesso...')