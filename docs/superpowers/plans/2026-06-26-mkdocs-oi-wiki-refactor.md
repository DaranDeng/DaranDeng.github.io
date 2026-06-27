# MkDocs OI Wiki Refactor Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Convert the current Jekyll-style personal learning repository into a clean MkDocs Material documentation site organized like a lightweight OI Wiki.

**Architecture:** MkDocs owns the build through `mkdocs.yml`, with published content under `docs/`. Subject notes and their related examples live together in `docs/<subject>/`, site assets live under `docs/assets/` and `docs/_static/`, and non-published legacy artifacts live under `archive/`.

**Tech Stack:** MkDocs, MkDocs Material, Python package dependencies, Markdown extensions, PowerShell migration checks, GitHub Pages deployment through GitHub Actions.

---

## File Structure

- Create `mkdocs.yml`: MkDocs site configuration, navigation, theme, Markdown extensions, static CSS and JavaScript references.
- Create `requirements.txt`: pinned Python documentation build dependencies.
- Create `.nojekyll`: prevents GitHub Pages from treating built output as Jekyll.
- Create `.github/workflows/deploy.yml`: builds and deploys MkDocs to GitHub Pages.
- Create `docs/index.md`: documentation portal homepage.
- Create `docs/_static/css/extra.css`: small OI Wiki-inspired custom styling on top of Material defaults.
- Create `docs/_static/js/extra.js`: reserved for local, non-template JavaScript hooks; initial file documents that no custom behavior is required.
- Create subject indexes:
  - `docs/ds-algorithm/index.md`
  - `docs/os/index.md`
  - `docs/computer-architecture/index.md`
  - `docs/ooad/index.md`
  - `docs/engineering-notes/index.md`
- Create `archive/binaries/` and `archive/raw/`: non-published historical storage.
- Modify or move Markdown, images, and examples from legacy top-level folders into `docs/`.
- Move current Jekyll-only files into `archive/raw/jekyll/` after the MkDocs build is proven to work:
  - `_config.yml`
  - `_layouts/default.html`
  - `css/variables.css`
  - `css/design-system.css`
  - `DESIGN_SYSTEM.md`

---

### Task 1: Add MkDocs Build Skeleton

**Files:**
- Create: `requirements.txt`
- Create: `mkdocs.yml`
- Create: `.nojekyll`
- Create: `.github/workflows/deploy.yml`

- [ ] **Step 1: Create the dependency file**

Use `apply_patch` to create `requirements.txt` with:

```text
mkdocs==1.6.1
mkdocs-material==9.5.49
mkdocs-git-revision-date-localized-plugin==1.3.0
```

- [ ] **Step 2: Create the MkDocs configuration**

Use `apply_patch` to create `mkdocs.yml` with:

```yaml
site_name: DaranDeng
site_description: Computer Science learning notes
site_author: Daran Deng
site_url: https://darandeng.github.io/

repo_name: DaranDeng/DaranDeng.github.io
repo_url: https://github.com/DaranDeng/DaranDeng.github.io
edit_uri: edit/main/docs/

theme:
  name: material
  language: zh
  features:
    - navigation.tabs
    - navigation.sections
    - navigation.indexes
    - navigation.top
    - toc.follow
    - search.suggest
    - search.highlight
    - content.code.copy
  palette:
    - scheme: default
      primary: blue
      accent: light blue
  font:
    text: Roboto
    code: Roboto Mono

nav:
  - Home: index.md
  - Data Structures and Algorithms:
      - ds-algorithm/index.md
  - Operating System:
      - os/index.md
  - Computer Architecture:
      - computer-architecture/index.md
  - OOAD:
      - ooad/index.md
  - Engineering Notes:
      - engineering-notes/index.md

markdown_extensions:
  - admonition
  - attr_list
  - def_list
  - footnotes
  - md_in_html
  - tables
  - toc:
      permalink: true
  - pymdownx.arithmatex:
      generic: true
  - pymdownx.details
  - pymdownx.highlight:
      anchor_linenums: true
  - pymdownx.inlinehilite
  - pymdownx.superfences
  - pymdownx.tasklist:
      custom_checkbox: true

plugins:
  - search:
      lang:
        - zh
        - en
  - git-revision-date-localized:
      enable_creation_date: true
      fallback_to_build_date: true

extra_css:
  - _static/css/extra.css

extra_javascript:
  - _static/js/extra.js
  - https://cdn.jsdelivr.net/npm/mathjax@3/es5/tex-mml-chtml.js

extra:
  generator: false
```

- [ ] **Step 3: Disable Jekyll processing for built pages**

Run:

```powershell
New-Item -ItemType File -Force -Path .nojekyll
```

- [ ] **Step 4: Add GitHub Pages deployment**

Use `apply_patch` to create `.github/workflows/deploy.yml` with:

```yaml
name: Deploy MkDocs site

on:
  push:
    branches:
      - main
  workflow_dispatch:

permissions:
  contents: read
  pages: write
  id-token: write

concurrency:
  group: pages
  cancel-in-progress: false

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - name: Checkout
        uses: actions/checkout@v4

      - name: Setup Python
        uses: actions/setup-python@v5
        with:
          python-version: '3.12'

      - name: Install dependencies
        run: pip install -r requirements.txt

      - name: Build site
        run: mkdocs build --strict

      - name: Upload artifact
        uses: actions/upload-pages-artifact@v3
        with:
          path: site

  deploy:
    environment:
      name: github-pages
      url: ${{ steps.deployment.outputs.page_url }}
    runs-on: ubuntu-latest
    needs: build
    steps:
      - name: Deploy to GitHub Pages
        id: deployment
        uses: actions/deploy-pages@v4
```

- [ ] **Step 5: Verify the skeleton configuration parses**

Run:

```powershell
python -m pip install -r requirements.txt
mkdocs build --strict
```

Expected: The install succeeds and the build fails only because referenced `docs/` pages or static files have not been created yet.

- [ ] **Step 6: Commit the skeleton**

Run:

```powershell
git add requirements.txt mkdocs.yml .nojekyll .github/workflows/deploy.yml
git commit -m "chore: add mkdocs build skeleton"
```

Expected: A commit is created with the MkDocs build entry points.

---

### Task 2: Add Documentation Portal And Subject Indexes

**Files:**
- Create: `docs/index.md`
- Create: `docs/_static/css/extra.css`
- Create: `docs/_static/js/extra.js`
- Create: `docs/ds-algorithm/index.md`
- Create: `docs/os/index.md`
- Create: `docs/computer-architecture/index.md`
- Create: `docs/ooad/index.md`
- Create: `docs/engineering-notes/index.md`

- [ ] **Step 1: Create the homepage**

Use `apply_patch` to create `docs/index.md` with:

```markdown
# DaranDeng Notes

这是一份计算机科学学习笔记，整理数据结构与算法、操作系统、计算机组成原理、OOAD 和工程手记等内容。

## 学习路线

1. 数据结构与算法
2. 操作系统
3. 计算机组成原理
4. 面向对象分析与设计
5. 工程手记

## 内容入口

- [Data Structures and Algorithms](ds-algorithm/)
- [Operating System](os/)
- [Computer Architecture](computer-architecture/)
- [OOAD](ooad/)
- [Engineering Notes](engineering-notes/)
```

- [ ] **Step 2: Create subject index pages**

Use `apply_patch` to create `docs/ds-algorithm/index.md` with:

```markdown
# Data Structures and Algorithms

数据结构与算法笔记，包含基础题型、经典算法、复杂度分析，以及配套代码示例。

## Sections

- 基础题型
- Algorithms and AI
- Linked List
- Binary Tree
- Stack
- Queue
- Union Find
- Examples
```

Use `apply_patch` to create `docs/os/index.md` with:

```markdown
# Operating System

操作系统笔记，覆盖进程、调度、并发、内存管理、磁盘与基础 API。

## Sections

- Basic API
- Process
- Scheduling
- Concurrency
- Memory Management
- Disk I/O
- Examples
```

Use `apply_patch` to create `docs/computer-architecture/index.md` with:

```markdown
# Computer Architecture

计算机组成原理与体系结构笔记，覆盖缓存、流水线、总线、I/O 和 GPU。

## Sections

- Overview
- Cache
- Pipelining and Superscalar
- Bus Architecture
- I/O Improvements
- GPU
```

Use `apply_patch` to create `docs/ooad/index.md` with:

```markdown
# Object-Oriented Analysis and Design

OOAD 笔记，包含 UML、类关系、包、SOLID、分层、序列图、威胁建模和设计模式。

## Sections

- UML Relationships
- Class Relationships
- Package
- SOLID and Layers
- Threat Modeling
- Design Patterns
- Examples
```

Use `apply_patch` to create `docs/engineering-notes/index.md` with:

```markdown
# Engineering Notes

工程手记用于收纳学习过程中的工具、环境、项目经验和复盘记录。
```

- [ ] **Step 3: Add minimal custom CSS**

Use `apply_patch` to create `docs/_static/css/extra.css` with:

```css
:root {
  --md-primary-fg-color: #2f6f9f;
  --md-primary-fg-color--light: #4f91c4;
  --md-primary-fg-color--dark: #1f4f75;
  --md-accent-fg-color: #1d8ec2;
}

.md-typeset h1,
.md-typeset h2 {
  font-weight: 700;
}

.md-typeset table:not([class]) {
  font-size: 0.78rem;
}

.md-typeset code {
  border-radius: 0.2rem;
}
```

- [ ] **Step 4: Add the JavaScript asset**

Use `apply_patch` to create `docs/_static/js/extra.js` with:

```javascript
// Reserved for small local documentation enhancements.
```

- [ ] **Step 5: Verify the portal builds**

Run:

```powershell
mkdocs build --strict
```

Expected: The build succeeds and creates `site/index.html`.

- [ ] **Step 6: Commit the portal and indexes**

Run:

```powershell
git add docs/index.md docs/_static/css/extra.css docs/_static/js/extra.js docs/ds-algorithm/index.md docs/os/index.md docs/computer-architecture/index.md docs/ooad/index.md docs/engineering-notes/index.md
git commit -m "feat: add documentation portal"
```

Expected: A commit is created with the new documentation entry pages.

---

### Task 3: Migrate Published Notes And Subject Assets

**Files:**
- Move: `basic_content_C++things.md` to `docs/ds-algorithm/cpp-basics.md`
- Move: `data-structure-and-algorithm/**` to `docs/ds-algorithm/**`
- Move: `OS/**` to `docs/os/**`
- Move: `Computer Architecture/**` to `docs/computer-architecture/**`
- Move: `OOAD/**` to `docs/ooad/**`
- Move: `工程手记/logKnowledge.md` to `docs/engineering-notes/log-knowledge.md`
- Modify: `mkdocs.yml`

- [ ] **Step 1: Move top-level Markdown into the docs tree**

Run:

```powershell
New-Item -ItemType Directory -Force -Path docs\ds-algorithm,docs\os,docs\computer-architecture,docs\ooad,docs\engineering-notes
git mv basic_content_C++things.md docs\ds-algorithm\cpp-basics.md
git mv "工程手记\logKnowledge.md" docs\engineering-notes\log-knowledge.md
```

Expected: The two files are staged as moves.

- [ ] **Step 2: Move subject folders into docs**

Run:

```powershell
git mv data-structure-and-algorithm\* docs\ds-algorithm\
git mv OS\* docs\os\
git mv "Computer Architecture\*" docs\computer-architecture\
git mv OOAD\* docs\ooad\
```

Expected: Existing Markdown, images, and source examples move under their subject folders. Empty legacy directories may remain in the working tree but are not tracked by Git.

- [ ] **Step 3: Move subject source examples into subject examples folders**

Run:

```powershell
New-Item -ItemType Directory -Force -Path docs\ds-algorithm\examples,docs\os\examples,docs\ooad\examples
git mv docs\os\concurenrcy.cpp docs\os\examples\concurrency.cpp
git mv docs\ooad\factory.cpp docs\ooad\examples\factory.cpp
git mv docs\ooad\solidExamples.py docs\ooad\examples\solid-examples.py
git mv docs\ds-algorithm\tanxinOfLeetCode.cpp docs\ds-algorithm\examples\tanxin-of-leetcode.cpp
git mv docs\ds-algorithm\huaweiTest.cpp docs\ds-algorithm\examples\huawei-test.cpp
```

Expected: The most visible standalone examples move to subject-level `examples/` folders.

- [ ] **Step 4: Archive binary outputs**

Run:

```powershell
New-Item -ItemType Directory -Force -Path archive\binaries
Get-ChildItem -Path docs -Recurse -File -Include *.exe | ForEach-Object {
  $target = Join-Path archive\binaries $_.Name
  git mv $_.FullName $target
}
```

Expected: `.exe` files are staged as moves into `archive/binaries/`.

- [ ] **Step 5: Add migrated pages to navigation**

Use `apply_patch` to replace the `nav:` block in `mkdocs.yml` with:

```yaml
nav:
  - Home: index.md
  - Data Structures and Algorithms:
      - Overview: ds-algorithm/index.md
      - C++ Basics: ds-algorithm/cpp-basics.md
      - BFS and DFS: ds-algorithm/bfs and dfs.md
      - Double Pointer: ds-algorithm/DoublePointer.md
      - Sliding Window: ds-algorithm/滑动窗口.md
      - Backtracking: ds-algorithm/回溯算法.md
      - Hashing: ds-algorithm/哈希法.md
      - Algorithms and AI:
          - Overview: ds-algorithm/Algorithms&AI/overview.md
          - Introduction: ds-algorithm/Algorithms&AI/algoIntro.md
          - Complexity: ds-algorithm/Algorithms&AI/Complexity.md
          - Factorial and Exponential: ds-algorithm/Algorithms&AI/Factorial&Exponential.md
          - Sort Algorithms: ds-algorithm/Algorithms&AI/sortAlgo.md
  - Operating System:
      - Overview: os/index.md
      - Basic API: os/BasicAPI.md
      - Notes: os/NotesOfOS.md
      - Lesson 1: os/lesson1.md
      - Process: os/process.md
      - Scheduling: os/scheduling.md
      - Concurrency: os/concurrency.md
      - Memory Management: os/memoryManagement.md
      - Disk I/O: os/diskIO.md
      - Hard Disk and RAID: os/Harddisk&RAID.md
  - Computer Architecture:
      - Overview: computer-architecture/index.md
      - Total Knowledge: computer-architecture/totalKnowledge.md
      - Tutorial 01: computer-architecture/tutorial01.md
      - Cache: computer-architecture/cache.md
      - Pipelining Superscalar: computer-architecture/Pipelining SuperScalar.md
      - Bus Architecture: computer-architecture/bus_architecture.md
      - I/O Improvements: computer-architecture/IO_improvements.md
      - GPU: computer-architecture/GPU.md
  - OOAD:
      - Overview: ooad/index.md
      - Class Relationship: ooad/week4_classRelationship.md
      - UML Relationship and Component: ooad/week5_UMLrelationship&Component.md
      - Package: ooad/week6_package.md
      - SOLID Layers and Sequence: ooad/week8_solid&layers&sequence.md
      - Threat Modeling: ooad/threatModeling.md
      - Observer: ooad/observer.md
  - Engineering Notes:
      - Overview: engineering-notes/index.md
      - Log Knowledge: engineering-notes/log-knowledge.md
```

- [ ] **Step 6: Verify migrated navigation**

Run:

```powershell
mkdocs build --strict
```

Expected: The build fails with concrete path or Markdown warnings if migrated file paths need correction; fix only the path errors reported by MkDocs and rerun until it succeeds.

- [ ] **Step 7: Commit migrated notes**

Run:

```powershell
git add docs archive mkdocs.yml
git commit -m "refactor: migrate notes into mkdocs docs tree"
```

Expected: A commit is created with migrated notes, subject assets, examples, and archived binaries.

---

### Task 4: Normalize Front Matter And Internal Links

**Files:**
- Modify: `docs/**/*.md`
- Create: `scripts/normalize-mkdocs-markdown.ps1`
- Create: `scripts/check-doc-links.ps1`

- [ ] **Step 1: Create a Markdown normalization script**

Use `apply_patch` to create `scripts/normalize-mkdocs-markdown.ps1` with:

```powershell
$ErrorActionPreference = 'Stop'

$files = Get-ChildItem -Path docs -Recurse -File -Filter *.md
foreach ($file in $files) {
  $content = Get-Content -LiteralPath $file.FullName -Raw

  if ($content -match '(?s)^---\s*\r?\n(.*?)\r?\n---\s*\r?\n') {
    $frontMatter = $Matches[1]
    $body = $content.Substring($Matches[0].Length)
    $titleLine = ($frontMatter -split "\r?\n" | Where-Object { $_ -match '^title:\s*(.+)$' } | Select-Object -First 1)

    if ($titleLine) {
      $title = ($titleLine -replace '^title:\s*', '').Trim()
      $content = "---`n$titleLine`n---`n`n$body"
    } else {
      $content = $body
    }
  }

  $content = $content -replace '\]\(/README\)', '](../)'
  $content = $content -replace '\]\(/OS/', '](../os/'
  $content = $content -replace '\]\(/Computer%20Architecture/', '](../computer-architecture/'
  $content = $content -replace '\]\(/data-structure-and-algorithm/', '](../ds-algorithm/'
  $content = $content -replace '\]\(/OOAD/', '](../ooad/'

  Set-Content -LiteralPath $file.FullName -Value $content -NoNewline -Encoding UTF8
}
```

- [ ] **Step 2: Run the normalization script**

Run:

```powershell
powershell -ExecutionPolicy Bypass -File scripts\normalize-mkdocs-markdown.ps1
```

Expected: Markdown files retain content, Jekyll-only `layout: default` front matter is removed, and basic absolute links are converted toward the new docs tree.

- [ ] **Step 3: Create a local link checker script**

Use `apply_patch` to create `scripts/check-doc-links.ps1` with:

```powershell
$ErrorActionPreference = 'Stop'

$missing = @()
$files = Get-ChildItem -Path docs -Recurse -File -Filter *.md
$pattern = '\[[^\]]+\]\((?!https?://|mailto:|#)([^)]+)\)'

foreach ($file in $files) {
  $content = Get-Content -LiteralPath $file.FullName -Raw
  $matches = [regex]::Matches($content, $pattern)

  foreach ($match in $matches) {
    $rawTarget = $match.Groups[1].Value
    $target = ($rawTarget -split '#')[0]
    if ([string]::IsNullOrWhiteSpace($target)) {
      continue
    }

    $decoded = [uri]::UnescapeDataString($target)
    $candidate = Join-Path $file.DirectoryName $decoded
    if (-not (Test-Path -LiteralPath $candidate)) {
      $missing += "$($file.FullName): $rawTarget"
    }
  }
}

if ($missing.Count -gt 0) {
  $missing | ForEach-Object { Write-Output $_ }
  exit 1
}

Write-Output 'All local Markdown links resolve.'
```

- [ ] **Step 4: Run link checks**

Run:

```powershell
powershell -ExecutionPolicy Bypass -File scripts\check-doc-links.ps1
```

Expected: The script exits `0` with `All local Markdown links resolve.` If it exits `1`, update the listed Markdown links and rerun.

- [ ] **Step 5: Verify MkDocs build after normalization**

Run:

```powershell
mkdocs build --strict
```

Expected: The build succeeds without Jekyll layout warnings.

- [ ] **Step 6: Commit normalized Markdown**

Run:

```powershell
git add docs scripts
git commit -m "fix: normalize markdown for mkdocs"
```

Expected: A commit is created with normalized front matter and internal links.

---

### Task 5: Archive Legacy Jekyll Structure And Document Compatibility

**Files:**
- Move: `_config.yml` to `archive/raw/jekyll/_config.yml`
- Move: `_layouts/default.html` to `archive/raw/jekyll/_layouts/default.html`
- Move: `css/variables.css` to `archive/raw/jekyll/css/variables.css`
- Move: `css/design-system.css` to `archive/raw/jekyll/css/design-system.css`
- Move: `DESIGN_SYSTEM.md` to `archive/raw/jekyll/DESIGN_SYSTEM.md`
- Move: root images to `archive/raw/root-images/`
- Modify: `README.md`
- Create: `docs/README.md`

- [ ] **Step 1: Archive Jekyll-only files**

Run:

```powershell
New-Item -ItemType Directory -Force -Path archive\raw\jekyll\_layouts,archive\raw\jekyll\css
git mv _config.yml archive\raw\jekyll\_config.yml
git mv _layouts\default.html archive\raw\jekyll\_layouts\default.html
git mv css\variables.css archive\raw\jekyll\css\variables.css
git mv css\design-system.css archive\raw\jekyll\css\design-system.css
git mv DESIGN_SYSTEM.md archive\raw\jekyll\DESIGN_SYSTEM.md
```

Expected: Legacy Jekyll configuration and CSS are preserved but removed from the published site root.

- [ ] **Step 2: Archive root image leftovers**

Run:

```powershell
New-Item -ItemType Directory -Force -Path archive\raw\root-images
git mv image.png archive\raw\root-images\image.png
git mv image-2.png archive\raw\root-images\image-2.png
git mv image-3.png archive\raw\root-images\image-3.png
git mv "{BC3F414B-3277-4D61-960D-3A26D467B554}.png" archive\raw\root-images\BC3F414B-3277-4D61-960D-3A26D467B554.png
```

Expected: Root images are preserved under archive storage.

- [ ] **Step 3: Replace the root README with project instructions**

Use `apply_patch` to replace `README.md` with:

```markdown
# DaranDeng.github.io

This repository contains a MkDocs Material documentation site for computer science learning notes.

## Local Development

Install dependencies:

```powershell
python -m pip install -r requirements.txt
```

Run the local server:

```powershell
mkdocs serve
```

Build the site:

```powershell
mkdocs build --strict
```

## Structure

- `docs/`: published notes, examples, and site assets
- `docs/_static/`: custom CSS and JavaScript
- `archive/`: preserved legacy files and generated binaries that are not published
- `mkdocs.yml`: site configuration and navigation
```

- [ ] **Step 4: Add a compatibility page for the old README route**

Use `apply_patch` to create `docs/README.md` with:

```markdown
# About This Site

The old `/README` entry now points readers to the MkDocs documentation portal.

Start from the [homepage](./) or use the navigation to browse notes by subject.
```

- [ ] **Step 5: Add the compatibility page to navigation**

Use `apply_patch` to add `- About: README.md` under the `Home` entry in `mkdocs.yml`:

```yaml
nav:
  - Home: index.md
  - About: README.md
```

Keep the subject navigation entries from Task 3 after these two entries.

- [ ] **Step 6: Verify archive cleanup**

Run:

```powershell
Get-ChildItem -Force | Select-Object Name
mkdocs build --strict
```

Expected: Root contains configuration, docs, archive, scripts, git metadata, and workflow metadata. The MkDocs build succeeds.

- [ ] **Step 7: Commit archive and README changes**

Run:

```powershell
git add README.md docs archive mkdocs.yml
git commit -m "chore: archive legacy jekyll structure"
```

Expected: A commit is created with the archived legacy files and updated README.

---

### Task 6: Final Verification And Local Preview

**Files:**
- Modify: only files needed to fix verification failures reported by commands in this task.

- [ ] **Step 1: Run a strict production build**

Run:

```powershell
mkdocs build --strict
```

Expected: Build succeeds and writes the generated site to `site/`.

- [ ] **Step 2: Confirm key output pages exist**

Run:

```powershell
Test-Path site\index.html
Test-Path site\ds-algorithm\index.html
Test-Path site\os\index.html
Test-Path site\computer-architecture\index.html
Test-Path site\ooad\index.html
Test-Path site\engineering-notes\index.html
```

Expected: Every command prints `True`.

- [ ] **Step 3: Confirm binaries are not published**

Run:

```powershell
Get-ChildItem -Path site -Recurse -File -Include *.exe
```

Expected: No files are printed.

- [ ] **Step 4: Start local preview**

Run:

```powershell
mkdocs serve -a 127.0.0.1:8000
```

Expected: The server starts at `http://127.0.0.1:8000/`. Keep this process running for browser verification, then stop it after checking pages.

- [ ] **Step 5: Browser smoke test**

Open these pages in a browser:

```text
http://127.0.0.1:8000/
http://127.0.0.1:8000/os/scheduling/
http://127.0.0.1:8000/ds-algorithm/Algorithms&AI/Complexity/
http://127.0.0.1:8000/computer-architecture/cache/
http://127.0.0.1:8000/ooad/observer/
```

Expected: Each page loads, the left navigation is visible, the right table of contents appears on long pages, and code blocks or tables render without broken layout.

- [ ] **Step 6: Stop the preview server**

Stop the `mkdocs serve` process with `Ctrl+C`.

Expected: The terminal returns to a PowerShell prompt.

- [ ] **Step 7: Commit verification fixes**

Run:

```powershell
git status --short
git add docs mkdocs.yml requirements.txt README.md scripts archive .github .nojekyll
git commit -m "fix: complete mkdocs verification cleanup"
```

Expected: If verification required fixes, a commit is created. If `git status --short` is empty, skip the commit.

---

## Self-Review

- Spec coverage: Tasks cover MkDocs setup, OI Wiki-style `docs/` organization, subject examples under `docs/**/examples/`, `docs/_static/` styling, `overrides/` reservation by non-use in first pass, archive handling, link compatibility, encoding caution, and final build verification.
- Red-flag scan: The plan contains no intentionally unresolved tasks. Any command that may report concrete failures includes the exact follow-up action: fix the reported path or link and rerun the same command.
- Type consistency: The paths used in `mkdocs.yml`, migration commands, verification commands, and target structure all use the same subject slugs: `ds-algorithm`, `os`, `computer-architecture`, `ooad`, and `engineering-notes`.
