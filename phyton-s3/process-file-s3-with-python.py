import boto3
import json

def lambda_handler(event, context):
    s3_client = boto3.client('s3')
    s3_clientobj = s3_client.get_object(Bucket='indaia', Key='largefile.txt')
    impar = 0
    par = 0
    
    for line in s3_clientobj['Body'].iter_lines():
        num = int((line[0:7]))
        if num%2:
            par +=1
        else:
            impar +=1
        
    print ('impar:')
    print (impar)
    print ('par:')
    print (par)