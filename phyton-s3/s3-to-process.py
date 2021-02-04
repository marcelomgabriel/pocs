## Accompanying code for - Processing large S3 files with AWS Lambda
## https://medium.com/swlh/processing-large-s3-files-with-aws-lambda-2c5840ae5c91

import csv
import json
import os

import boto3
import botocore.response

MINIMUN_REMAINING_TIME_MS = int(os.getenv('MINIMUM_REMAINING_TIME_MS')) or 10000


def lambda_handler(event, context):
    print ('begin')
    bucket_name = event['transacoes']
    object_key = event['file1']
    offset = event.get('offset', 0)
    fieldnames = event.get('fieldnames', None)

    s3_resource = boto3.resource('s3')
    s3_object = s3_resource.Object(bucket_name=bucket_name, key=object_key)
#    bodylines = get_object_bodylines(s3_object, offset)
#
#    csv_reader = csv.DictReader(bodylines.iter_lines(), fieldnames=fieldnames)
#    for row in csv_reader:
#        ## process and do work
#        if context.get_remaining_time_in_millis() < MINIMUN_REMAINING_TIME_MS:
#            fieldnames = fieldnames or csv_reader.fieldnames
#            break
#    
#    new_offset = offset + bodylines.offset
#    if new_offset < s3_object.content_length:
#        new_event = {
#            **event,
#            "offset": new_offset,
#            "fieldnames": fieldnames
#        }
#        invoke_lambda(context.function_name, new_event)
#    return


#def invoke_lambda(function_name, event):
#    payload = json.dumps(event).encode('utf-8')
#    client = boto3.client('lambda')
#    response = client.invoke(
#        FunctionName=function_name,
#        InvocationType='Event',
#        Payload=payload
#    )


#def get_object_bodylines(s3_object, offset):
    resp = s3_object.get(Range=f'bytes={offset}-')
    body: botocore.response.StreamingBody = resp['Body']
    return BodyLines(body)


#class BodyLines:
#    def __init__(self, body: botocore.response.StreamingBody, initial_offset=0):
#        self.body = body
#        self.offset = initial_offset
#
#    def iter_lines(self, chunk_size=1024):
#        """Return an iterator to yield lines from the raw stream.
#        This is achieved by reading chunk of bytes (of size chunk_size) at a
#        time from the raw stream, and then yielding lines from there.
#        """
#        pending = b''
#        for chunk in self.body.iter_chunks(chunk_size):
#            lines = (pending + chunk).splitlines(True)
#            for line in lines[:-1]:
#                self.offset += len(line)
#                yield line.decode('utf-8')
#            pending = lines[-1]
#        if pending:
#            self.offset += len(pending)
#            yield pending.decode('utf-8')