import json
import boto3
import time

def lambda_handler(event, context):
    client = boto3.client('athena')

    #Setup and perform query
    queryStart = client.start_query_execution(
        QueryString = 'SELECT * FROM "transacoes-database"."transacoes_enviadas" WHERE nsu = 4 ;',
        QueryExecutionContext ={
            'Database' : 'default'
        },
        ResultConfiguration ={
            'OutputLocation' : 's3://transacoes-processadas/'
        }
    )

#Observe results
    queryId = queryStart['QueryExecutionId']
    time.sleep(15)

    results = client.get_query_results(QueryExecutionId = queryId)
    for row in results ['ResultSet'] ['Rows']:
        print(row)