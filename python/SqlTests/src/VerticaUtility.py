'''
Created on Apr 4, 2015

@author: cdongsi
'''

from vertica_python import connect

import logging
import MyLogger
myLogger = logging.getLogger('VerticaUtility')

def getVerticaConnection(username, password, url):
    '''
    Return a JDBC connection to a Vertica server.
    
    TODO: update argument parsing to include port and database
    '''
    
    myLogger.debug("%s, %s, %s", username, password, url )
    
    connection = connect({
                          'host' : '192.168.5.133',
                          'user' : username,
                          'password' : password,
                          'database' : 'VMart'
                          })
    
    return connection

def useVerticaSchema(conn, schema):
    '''
    Use the schema, specified by name.
    For multiple schemas, separate them by ','. E.g.: "public, v_catalog"
    Basically send the following SQL query to Vertica: 
    set search_path to schemaName1, schemaName2
    
    TODO: schema is @args
    '''
    pass

