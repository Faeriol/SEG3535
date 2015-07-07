#!/usr/bin/env python

import sys
from flask import Flask, render_template
from flask_bootstrap import Bootstrap
from flask_wtf import Form, RecaptchaField
from flask_wtf.file import FileField
from flask import request
from wtforms import TextField, HiddenField, ValidationError, RadioField,\
    BooleanField, SubmitField, IntegerField, FormField, validators
from wtforms.validators import Required

app = Flask(__name__)
Bootstrap(app)

@app.route('/book/<id>') # Where ID's are actually ISBN? 
def book(id):
    pass # Would crashes anyways bros
    # Mock
    # Fetch bookinfo
    return render_template('book.html', form=book)

@app.route('/search/', methods=["GET"])
def search():
    print(request)
    term = request.args.get('term')
    ## Here mock behavior, probably render some prepared template
    return "You searched for " +term

@app.route('/category/<cat>')
def category(cat):
    # we has got partial feature... Put in some books
    return render_template("category.html")

@app.route('/')
def index():
    # Need to display some crap here
    return render_template("index.html")

if __name__=="__main__":
    app.debug = True # Verbose browser output. Evil in prod.
    app.run()
