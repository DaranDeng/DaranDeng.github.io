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
