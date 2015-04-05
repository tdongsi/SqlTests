'''
Created on Apr 4, 2015

@author: cdongsi
'''

import logging

# set up logging to file
LOG_FILENAME = 'PySqlTest.log'
# Additional logging info: %(asctime)s %(name)-12s 
logging.basicConfig(level=logging.DEBUG,
                    format='%(asctime)s {%(name)-12s} %(levelname)-8s %(message)s',
                    datefmt='%m-%d %H:%M',
                    filename=LOG_FILENAME,
                    filemode='w')

# define a Handler which writes INFO messages or higher to the sys.stderr
console = logging.StreamHandler()
console.setLevel(logging.INFO)
# set a format which is simpler for console use
formatter = logging.Formatter('%(name)-12s: %(levelname)-8s %(message)s')
# tell the handler to use this format
console.setFormatter(formatter)
# add the handler to the root logger
logging.getLogger('').addHandler(console)

        
if __name__ == "__main__":
    print "My configured logging module."