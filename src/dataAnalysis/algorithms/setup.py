#!/usr/bin/env python

from setuptools import setup, find_packages

from algorithms import __version__ as version

maintainer = 'somewhat6139'
maintainer_email = 'swh6139@163.com'
author = maintainer
author_email = maintainer_email
description = "A pure Python library designed to make it easy and quick for data analysis"

install_requires = [
    'numpy>=1.13.1',
    'matplotlib>=2.0.2',
    'scipy>=0.19.1',
    'scikit-learn>=0.19.0',
]

license = 'LICENSE'

name = 'algorithms'
packages = [
    'algorithms',
]
platforms = ['linux']
url = 'https://github.com/somewhat6139/cloudAnalysis/tree/Develop/src/dataAnalysis/algorithms'
download_url = ''
classifiers = [
    'Development Status :: 1 - Alpha',
    'Topic :: Data Analysis',
    'License :: OSI Approved :: MIT License',
    'Programming Language :: Python :: 2.7',
]

setup(author=author,
      author_email=author_email,
      description=description,
      license=license,
      install_requires=install_requires,
      maintainer=maintainer,
      name=name,
      packages=find_packages(),
      platforms=platforms,
      url=url,
      download_url=download_url,
      version=version,
#      test_suite="tests",
      classifiers=classifiers)
