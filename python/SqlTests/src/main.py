'''
Created on Apr 4, 2015

Usage: main.py [-h] -database URL -username USERNAME -password PASSWORD -file
               INPUTFILE [-schema SCHEMA]

Script to run SQL scripts and annotated tests in it.

optional arguments:
  -h, --help          show this help message and exit
  -database URL       Test database's JDBC URL.
  -username USERNAME  Login credentials.
  -password PASSWORD  Login credentials.
  -file INPUTFILE     SQL file to be tested.
  -schema SCHEMA      Vertica schema to be tested.
  
Example: Running test.sql against the Vertica database on a local VM

python main.py -username dbadmin -password password 
-database jdbc:vertica://192.168.5.133:5433/VMart -file test.sql -schema IDEA

@author: cdongsi
'''

import argparse
import VerticaUtility
from vertica_python import connect

import logging
import MyLogger
myLogger = logging.getLogger('PySqlTest')

class SqlRunner(object):
    pass

def main():
    '''
    Main method: perform argument parsing and start running scripts.
    '''
    
    parser = argparse.ArgumentParser(description='Script to run SQL scripts ' 
        'and annotated tests in it.')
    
    parser.add_argument('-host', action='store', required=True,
                        dest='host', help='Test database\'s host.')
    
    parser.add_argument('-port', action='store', default=5433, type=int,
                        dest='port', help='Test database\'s port number.')
    
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
    parser.add_argument('-database', action='store', dest='database',
                        default='VMart',
                        help='Vertica schema to be tested.')
    
    parser.add_argument('-schema', action='store', dest='schema',
                        required=False,
                        help='Vertica schema to be tested.')
    
    args = parser.parse_args()
    myLogger.debug("Database host: %s:%s", args.host, args.port)
    myLogger.debug("Username: %s", args.username)
    # DO NOT log password
    myLogger.debug("SQL file: %s", args.inputfile)
    myLogger.debug("Database name: %s", args.database)
    myLogger.debug("Schema name: %s", args.schema)
    
    conn = connect(dict(
                host = args.host,
                port = args.port,
                user = args.username,
                password = args.password,
                database = args.database
                ))
    '''
    Equivalent to standard usage:
    
    connection = connect({
    'host': '127.0.0.1',
    'port': 5433,
    'user': 'some_user',
    'password': 'some_password',
    'database': 'a_database'
    })
    '''
    
    if conn is not None:
        myLogger.info('Connected to database')
    
        if args.schema is not None:
            pass
        
    else:
        myLogger.error('Could not connect to the specified database.')
    

if __name__ == '__main__':
    ''' Usage: See the top comment for usage of this script.'''
    
    print 'Running SQL tests.'
    main()
