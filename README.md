# tv-password-helper
Lowering your effort of entering passwords through TV remotes.

## Warning
Passwords are sensitive data, so you should actually not trust this tool - you can however check the source code and see that the following is true
- it will not store or expose your passwords
- it will not use the passwords in any way

Also keep in mind
- it says nothing about the security or quality of your passwords, just estimates their input effort
- it might suggest passwords too simple to be of any use - it's up to you to decide if you want to use the proposed password

## How it works

This tools accepts password candidates line-by-line, e.g. from a password manager ([KeePass](https://keepass.info) has a preview function which generates ~30 passwords of a given complexity) or from an online tool like https://manytools.org/network/password-generator/ (but it's up to **you** to check the quality and integrity of the passwords generated).

Finish your input with an empty line. Then, the tool will estimate the *effort* to enter each password (approximate distance to cover on the TV on-screen keyboard) and choose the simplest one (but not simpler than a given hardness threshold, defaults to 0.55 of the most complex candidate).

The following illustrates the usage and shows the output (candidate + effort):

```
Wt4U^Hm6?y
f7Evz}W+V^
aD}@bQm(H6
^mrWs6z-fM
j%mBQ&Z2.k

Got 5 words with distance up to 122
Proposing aD........ (81)
```

## How to run it
Set up the project, run `gradlew build` and start `main.kt`. Or use IntelliJ IDEA.