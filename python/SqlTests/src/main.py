'''
Created on Apr 4, 2015

@author: cdongsi
'''

import argparse

import logging
import MyLogger
myLogger = logging.getLogger('PySqlTest')

class SqlRunner(object):
    pass

def main():
    '''
    TODO
    '''
    
    parser = argparse.ArgumentParser(description='Script to run SQL scripts ' 
        'and annotated tests in it.')
    
    parser.add_argument('-database', action='store', required=True,
                        dest='url', help='Test database\'s JDBC URL.')
    
    parser.add_argument('-username', action='store', dest='username',
                        required=True,
                        help='Login credentials.')
    
    parser.add_argument('-password', action='store', dest='password',
                        required=True,
                        help='Login credentials.')
    
    parser.add_argument('-file', action='store', dest='inputfile',
                        required=True,
                        help='SQL file to be tested.')
    
    # Vertica-specific optional argument.
    parser.add_argument('-schema', action='store', dest='schema',
                        required=False,
                        help='Vertica schema to be tested.')
    
    args = parser.parse_args()
    myLogger.debug("Database URL: %s", args.url)
    myLogger.debug("Username: %s", args.username)
    # DO NOT log password
    myLogger.debug("SQL file: %s", args.inputfile)
    # TODO: default value for schema
    myLogger.debug("Schema name: %s", args.schema)

if __name__ == '__main__':
    main()
