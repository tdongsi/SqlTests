'''
Created on Apr 4, 2015

@author: cdongsi
'''


import logging
import MyLogger
myLogger = logging.getLogger('VerticaUtility')


def useVerticaSchema(conn, schema):
    '''
    Use the schema, specified by name.
    For multiple schemas, separate them by ','. E.g.: "public, v_catalog"
    Basically send the following SQL query to Vertica: 
    set search_path to schemaName1, schemaName2
    
    TODO: schema is @args
    '''
    pass

