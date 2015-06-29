#!/bin/env python

import httplib2
from bs4 import BeautifulSoup
import sys

#BASE_URL = "http://medlibrary.org/lib/rx/meds/"
BASE_URL = "http://medlibrary.org/medwiki/"
BANNED = ["Main Menu", "RSS Feeds", "About", "Share this Drug Information", "Medication Section", "Trustworthy Health Information", "Prescription Medications", "Share This Page", "External links", "References", "Open Source Encyclopedia", "Patents"]

def isBanned(s):
    for b in BANNED:
        if b in s.text:
            return True
    return False


if __name__=="__main__":
    if len(sys.argv) <= 1:
        print("Need an arg")
        sys.exit()
    print(BASE_URL +sys.argv[1])
    http = httplib2.Http()
    status, response = http.request(BASE_URL + sys.argv[1])
    soup = BeautifulSoup(response)
    for h2 in soup.findAll("h2"):
        if h2.has_attr("class"):
            continue
        if isBanned(h2):
            continue
        print(h2)
        n = h2.next_sibling
        while n is not None:
            if n.name == "h2":
                break
            if n.name == "footer":
                break
            if n.name == "aside":
                n = n.next_sibling
                continue
            print(n)
            n = n.next_sibling
    #print(soup.prettify())
