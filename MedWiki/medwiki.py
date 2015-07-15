#!/bin/env python

import httplib2
from bs4 import BeautifulSoup
import sys
from flask import Flask
app = Flask(__name__)

#BASE_URL = "http://medlibrary.org/lib/rx/meds/"
BASE_URL = "http://medlibrary.org/medwiki/"
BANNED = ["Main Menu", "RSS Feeds", "About", "Share this Drug Information", "Medication Section", "Trustworthy Health Information", "Prescription Medications", "Share This Page", "External links", "References", "Open Source Encyclopedia", "Patents"]

def isBanned(s):
    for b in BANNED:
        if b in s.text:
            return True
    return False

def getMedHTML(med):
    #print(BASE_URL +sys.argv[1])
    http = httplib2.Http()
    status, response = http.request(BASE_URL + med)
    soup = BeautifulSoup(response)
    soup.header.extract()
    [x.extract() for x in soup.findAll('form')]
    [x.extract() for x in soup.findAll('style')]
    [x.extract() for x in soup.findAll('link')]
    [x.extract() for x in soup.findAll('input')]
    [x.extract() for x in soup.findAll('script')]
    [x.extract() for x in soup.findAll('footer')]
    [x.extract() for x in soup.findAll('aside')]
    [x.extract() for x in soup.findAll('li') if x.has_attr("class")]
    for h2 in soup.findAll("h2"):
        if h2.has_attr("class"):
            h2.extract()
            continue
        if isBanned(h2):
            h2.extract()
            continue
        #print(h2)
        n = h2.next_sibling
        while n is not None:
            if n.name == "h2":
                break
            n = n.next_sibling
    #print(out)
    return str(soup)

@app.route('/wiki/<medname>')
def wiki(medname):
    return getMedHTML(medname)

if __name__=="__main__":
    app.debug = True
    app.run()
