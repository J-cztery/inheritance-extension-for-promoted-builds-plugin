This project contains the source code of the extension to "Inheritance Plugin" for
Jenkins to provide interaction between "Promoted builds plugin" and "Inheritance plugin".

The source code is released under the LGPL v3. You can find the full
licence text in the LICENCE file.


Inheritance projects allow you to define
common properties only once and inherit them to multiple projects. Ideal
candidates for this are, for example, the SCM setup, common pre-build and
post-build steps as well as cleanup and logging properties.

Unfortunately promoted builds plugin does not work properly out of the box with inheritance plugin, hence the extension. 

The problem has not been solved in the most elegant way ever (using reflection to call protected method), it does the job however. Feel free to provide better solution. 

