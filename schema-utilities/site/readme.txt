To create the complete documentation you need to prepare the schema tree.

1. Make sure you have committed all changes, since you will be reverting them after doing this
2. Make sure all schema projects are uncommented in the parent pom
3. Drop all the versions to the current release version (ie., 1.0.2-SNAPSHOT -> 1.0.1)
4. Do: mvn clean site
5. Revert all the changes