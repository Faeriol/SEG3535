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

ISBN = 4
CATEGORY = 2
CATEGORIES = ["Science", "Technology", "Fantasy"]

app = Flask(__name__)

def getData():
    result = []
    with open('data.csv') as data:
        reader = csv.reader(data, delimiter='|', quotechar='"', quoting=csv.QUOTE_ALL)
        for row in reader:
            result += [row]
    return result

@app.route('/book/<id>') # Where ID's are actually ISBN
def book(id):
    result = []
    for book in getData():
        if id == book[ISBN]:
            result = book
            break
    return render_template('book.html', form=result)

@app.route('/search/', methods=["GET"])
def search():
    print(request)
    term = request.args.get('term')
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
                books += [book]
                break
    return render_template("search.html", result={"header":["Title", "Author", "Category", "Description", "ISBN" ],"data":books})

@app.route('/category/<cat>')
def category(cat):
    result = []
    for book in getData():
        if id == book[ISBN]:
            result = book
            break
    # we has got partial feature... Put in some books
    return render_template("category.html")

@app.route('/')
def index():
    # Need to display some crap here
    return render_template("index.html")

if __name__=="__main__":
    Bootstrap(app)
    app.debug = True # Verbose browser output. Evil in prod.
    app.config['SECRET_KEY'] = 'devkey'
    app.run()
