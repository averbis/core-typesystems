---
name: "📦 Feature Release"
about: Checklist for the creation of a feature (or major) release
type: "📦 Release"
---

##### Feature release checklist

Release checklist for feature releases that increase the first or second version digit.

**Immediate actions**
- [ ] Use the Maven Dependency Plugin to check for dependencies that can be upgraded and upgrade them
  `mvn versions:display-dependency-updates -Dversions.outputLineWidth=200 -DallowMajorUpdates=false | grep -e '->' | sort | uniq`

**GitHub**
- [ ] Create a new milestone for the next version (bugfix or feature)
- [ ] Check that all PR have been merged and issues are resolved or moved to the next milestone (except this release issue)
- [ ] Check that end date of the milestone is set
- [ ] If there is a bugfix milestone that is superseded by this feature release, move all issues and PRs from the bugfix milestone to the current release milestone, then delete the bugfix milestone
- [ ] Close the milestone

**Local**
- [ ] Create a maintenance branch based on the main branch and push it. E.g. if you are going to 
      release `3.3.0`, then use the branch name `release/3.3.x`.

**[GitHub](https:../actions/workflows/maven-release-build.yml)**
- [ ] Trigger a release build of the maintenance branch. The **new** version should be the version of
      the next bugfix release. E.g. if you release `3.3.0`, then the new version should be `3.3.1-SNAPSHOT`.

**Local**
- [ ] Merge the maintenance branch into the main branch using `git merge -s ours <branchname>`
- [ ] Update the version in the POM files on the main branch to the next feature release version.
      E.g. if you released `3.3.0`, update to `3.4.0-SNAPSHOT` ( 
      `mvn versions:set -DoldVersion='3.3.0-SNAPSHOT' -DnewVersion='3.4.0-SNAPSHOT'; mvn versions:commit`).
- [ ] Push the changes to the main branch

**GitHub**
- [ ] Create a release in GitHub and auto-generate the release notes

**Post-release**
- [ ] Create a new release issue for the next feature release and perform the immediate actions
