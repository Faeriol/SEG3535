#!/usr/bin/env python

import sys
from flask import Flask, render_template, flash
from flask_bootstrap import Bootstrap
from flask_wtf import Form, RecaptchaField
from flask_wtf.file import FileField
from flask import request
from wtforms import TextField, HiddenField, ValidationError, RadioField,\
    BooleanField, SubmitField, IntegerField, FormField, validators
from wtforms.validators import Required
import csv

# ID Fields in CSV... Yes this is dirty
HEADER = ["Title", "Author", "Category", "Description", "ISBN", "Price", "tags" ]
TAGS = 6
THEAD = HEADER[0:TAGS] # skip tags when displaying
ISBN = 4
CATEGORY = 2
CATEGORIES = ["Science", "Technology", "Fantasy"]


app = Flask(__name__)

def dropTags(row):
    return row[0:TAGS]

def extractTags(row):
    nrow = dropTags(row)
    nrow += [row[TAGS].split('-')]
    print nrow
    return nrow

def getData():
    result = []
    with open('data.csv') as data:
        reader = csv.reader(data, delimiter='|', quotechar='"', quoting=csv.QUOTE_ALL)
        for row in reader:
            result += [dropTags(row)]
    return result

def getDataWithTags():
    result = []
    with open('data.csv') as data:
        reader = csv.reader(data, delimiter='|', quotechar='"', quoting=csv.QUOTE_ALL)
        for row in reader:
            result += [extractTags(row)]
    return result
    
def notDoneYet(feature):
    return render_template('notdone.html', key=feature)

# Future features
@app.route('/checkout')
def checkout():
    return render_template('checkout.html')

@app.route('/delete')
def delete():
    return notDoneYet("Delete")

# Yeah I know this should have variables and whatnot but whatevs
@app.route('/cart')
def cart():
    "Lets build a fake basket"
    cart = getData()
    return render_template('cart.html', content="cart", result={"header":THEAD,"data":cart})

@app.route('/book/<id>') # Where ID's are actually ISBN
def book(id):
    result = []
    for book in getData():
        if id == book[ISBN]:
            result = book
            break
    return "banane"
    return render_template('book.html', form=result)

@app.route('/search/', methods=["GET"])
def search():
    print(request)
    term = request.args.get('term')
    found = False
    books = []
    if term == None:
        flash('Please use the search field to find content.', 'error')
        return render_template("search.html", result={"header":[],"data":[]})
    if term == "":
        flash('The search term was empty. Please provide a valid search term', 'error')
        return render_template("search.html", result={"header":[],"data":[]})
    for book in getData():
        for field in book:
            if term in field:
                print("FOUND")
                found=True;
                books += [book]
                break
    if(found):
        return render_template("search.html", result={"query":"'" +term +"'","header":HEADER,"data":books})
    else:
        return render_template("search.html", result={"query":"'" +term +"'", "data":[]})


@app.route('/category/<cat>')
def category(cat):
    books = []
    cat=cat.title()
    for book in getData():
        if cat == book[CATEGORY]:
            books += [book]
    # we has got partial feature... Put in some books
    return render_template("category.html", result={"cat":cat, "header":HEADER, "data":books})

@app.route('/')
def index():
    # Need to display some crap here
    return render_template("index.html", content="home")

if __name__=="__main__":
    Bootstrap(app)
    app.debug = True # Verbose browser output. Evil in prod.
    app.config['SECRET_KEY'] = 'devkey'
    app.run()
