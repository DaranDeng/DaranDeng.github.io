# MkDocs OI Wiki Refactor Design

## Goal

Refactor this forked personal learning repository into a clean, OI Wiki-inspired documentation site. The site should feel like a lightweight knowledge base rather than a portfolio landing page: searchable, structured by subject, easy to scan, and maintainable over time.

The refactor has two equal priorities:

- Improve the website layout and reading experience.
- Clean the repository structure so published content, examples, assets, and archived files have clear homes.

## Reference Direction

The primary reference is the OI Wiki repository and site structure:

- Content lives under `docs/`.
- Site navigation is controlled by `mkdocs.yml`.
- Code examples can live inside the relevant documentation tree.
- Static customization can live under documentation static asset paths such as `docs/_static/`.

This project should follow those conventions where they fit a personal site, while avoiding the full complexity needed by a large collaborative documentation project.

## Chosen Approach

Use MkDocs with the Material theme as the documentation framework.

This replaces the current Jekyll/GitHub Pages layout with a documentation-oriented build:

- `mkdocs.yml` becomes the main site configuration.
- `docs/` becomes the published documentation root.
- Markdown pages move into subject-based folders.
- Subject-related code examples stay inside `docs/**/examples/`.
- Static site customization goes through `docs/_static/`.
- `overrides/` is reserved as the standard MkDocs Material template override entry point, but the first implementation should avoid custom templates unless necessary.
- Old binaries, generated files, and unclassified historical material move to `archive/` and are not published.

## Target Repository Structure

```text
/
  mkdocs.yml
  docs/
    index.md
    _static/
      css/
        extra.css
      js/
    assets/
    ds-algorithm/
      index.md
      examples/
    os/
      index.md
      examples/
    computer-architecture/
      index.md
    ooad/
      index.md
      examples/
    engineering-notes/
      index.md
    superpowers/
      specs/
  overrides/
  archive/
    binaries/
    raw/
```

## Content Migration Rules

Current directories map into the new documentation tree as follows:

```text
data-structure-and-algorithm/ -> docs/ds-algorithm/
OS/                           -> docs/os/
Computer Architecture/        -> docs/computer-architecture/
OOAD/                         -> docs/ooad/
engineering notes folder       -> docs/engineering-notes/
```

Markdown files that are meant to be read on the site should move into `docs/`.

Source files that support a subject should move into the closest subject-level `examples/` folder. Examples include C, C++, Java, and Python learning snippets that clarify a note or lesson.

Images should move near the related content when they are page-specific, or into `docs/assets/` when shared across pages.

Binary files such as `.exe` outputs should move to `archive/binaries/` and should not be part of the generated site.

Unclear historical files should move to `archive/raw/` only when they are not needed by published pages.

## Navigation Design

The site navigation should be explicit in `mkdocs.yml`, with top-level sections for:

- Home
- Data Structures and Algorithms
- Operating System
- Computer Architecture
- Object-Oriented Analysis and Design
- Engineering Notes

Each subject should start with an `index.md` overview page that introduces the folder and links to important notes. The navigation should prefer stable English slugs for paths, even when page titles remain Chinese.

## Page Experience

The site should use an OI Wiki-like documentation layout:

- Top site header with search.
- Left navigation for subject and page structure.
- Center reading column.
- Right in-page table of contents when supported by the theme.
- Clean typography and restrained color.
- Good rendering for code blocks, tables, blockquotes, and math formulas.

The homepage should act as a documentation portal rather than a full-screen hero. It should show the project purpose, subject entry points, and a recommended learning route.

## Styling

Use MkDocs Material defaults first. Add only small customizations in:

```text
docs/_static/css/extra.css
```

The first implementation should not build a custom theme. `overrides/` remains available for future template work but should stay minimal unless a concrete need appears.

## Link Compatibility

The new site should use clean paths based on the new folder structure. Important legacy entry points should remain discoverable through compatibility pages or redirect-style pages where practical.

Priority compatibility targets:

- `/`
- `/README`
- Frequently linked subject pages such as OS scheduling and core algorithm notes.

Full compatibility for every old path is not required in the first pass because many old paths include spaces, special characters, Chinese slugs, and symbols such as `&`. The implementation should document any known broken legacy paths.

## Encoding And Content Integrity

Several Chinese files appear garbled in the current PowerShell output. Before rewriting content, the implementation should determine whether this is only terminal output encoding or actual file corruption.

The first refactor should avoid rewriting note bodies unless required for navigation, front matter, image paths, or build correctness.

## Build And Verification

The implementation is complete only when:

- MkDocs configuration exists and builds locally.
- Homepage renders as a documentation portal.
- At least one page from each main subject renders.
- Navigation links work for migrated pages.
- Images referenced by sampled pages load correctly.
- Code blocks and math syntax render acceptably.
- Binaries and generated artifacts are no longer in the published documentation tree.
- The repository root is reduced to configuration, documentation, archive, and project metadata.

## Non-Goals

The first pass will not:

- Rewrite all learning notes for style or correctness.
- Implement the full OI Wiki contribution and deployment system.
- Preserve every legacy URL.
- Build a custom Material template unless defaults plus `extra.css` are insufficient.
- Delete historical files without first moving them to `archive/`.
