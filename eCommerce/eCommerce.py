#!/usr/bin/env python

import sys
from flask import Flask, render_template
from flask_bootstrap import Bootstrap
from flask_wtf import Form, RecaptchaField
from flask_wtf.file import FileField
from wtforms import TextField, HiddenField, ValidationError, RadioField,\
    BooleanField, SubmitField, IntegerField, FormField, validators
from wtforms.validators import Required

app = Flask(__name__)
Bootstrap(app)

@app.route('/book/<id>') # Where ID's are actually ISBN? 
def book(id):
    pass # Would crashes anyways bros
    # Fetch bookinfo
    return render_template('book.html', form=book)
@app.route('/search/<term>')
def search(term):
    pass
    # Create search result page

@app.route('/category/<cat>')
def category(cat):
    return render_template("category.html")

@app.route('/')
def index():
    return render_template("index.html")

if __name__=="__main__":
    app.debug = True
    app.run()
