---
name: "📦 Bug-fix Release"
about: Checklist for the creation of a bug-fix release
type: "📦 Release"
---

##### Bug-fix release checklist

Release checklist for feature releases that increase the third/last version digit.

**GitHub**
- [ ] Create a new milestone for the next version (bugfix or feature)
- [ ] Check that all PR have been merged and issues are resolved or moved to the next milestone (except this release issue)
- [ ] Check that end date of the milestone is set
- [ ] Close the milestone

**Local**
- [ ] Ensure that the maintenance branch has been merged into the main branch

**[GitHub](https:../actions/workflows/maven-release-build.yml)**
- [ ] Trigger a release build of the maintenance branch

**Local**
- [ ] Merge the maintenance branch into the main branch using `git merge -s ours <branchname>` and push

**GitHub**
- [ ] Create a release in GitHub and auto-generate the release notes
