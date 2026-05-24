# Design System Documentation

This document outlines the design system for DaranDeng.github.io, based on Dieter Rams' design principles and Swiss Style grid systems.

## Philosophy

The design system is built on three core principles:

1. **Dieter Rams - "Less, but better"**: Every element must justify its existence. Remove until it breaks, then add back only what's essential.
2. **Swiss Style Grid System**: Mathematical precision creates visual harmony through grid-based rationalism.
3. **Material Honesty**: Each material should look and feel like itself, with no skeuomorphic decorations.

## Color System (60-30-10 Rule)

### 60% - Neutral Base
Calm, museum-like environment
- **Cream**: `#F0EFE9`
- **White**: `#FFFFFF`

### 30% - Material Definition
Subtle gradients for materials
- **Light**: `#E8E8E6`
- **Medium**: `#D8D8D8`
- **Dark**: `#C8C8C8`

### 10% - Functional Accents
Interactive elements ONLY
- **Orange**: `#F4793E` (Primary actions)
- **Green**: `#3A6B5A` (Supporting)
- **Blue**: `#2E4A7A` (Information)

## Typography System

### Font Family
- **Primary**: Helvetica Neue (Grotesque sans-serif)
- **Fallback**: Arial
- **Monospace**: Monaco, Courier New

### Modular Scale
Base: 14px | Ratio: 1.25

| Class | Size | Usage |
|-------|------|-------|
| `--font-size-xs` | 10px | Labels, small text |
| `--font-size-sm` | 11px | Small descriptions |
| `--font-size-base` | 14px | Body text |
| `--font-size-md` | 16px | Small headings |
| `--font-size-lg` | 20px | Subtitles |
| `--font-size-xl` | 24px | Subheadings |
| `--font-size-2xl` | 30px | Section headings |
| `--font-size-3xl` | 38px | Large headings |
| `--font-size-4xl` | 48px | Section titles |
| `--font-size-5xl` | 60px | Hero titles |
| `--font-size-6xl` | 75px | Large hero |
| `--font-size-7xl` | 94px | Extra large hero |
| `--font-size-8xl` | 110px | Maximum hero |

### Font Weights
- **Regular**: 400 (body text, descriptions)
- **Medium**: 500 (subtitles, secondary info)
- **Semibold**: 600 (section titles, labels)
- **Bold**: 700 (product name, primary titles)

### Letter Spacing
- **Tight**: -0.02em (large text)
- **Normal**: 0 (default)
- **Loose**: 0.05em (small text for readability)

## Spacing System

Base Unit: **8px** (all spacing derives from multiples and divisions)

| Size | Value | Multiplier |
|------|-------|------------|
| xs | 4px | 0.5x |
| sm | 8px | 1x |
| md | 16px | 2x |
| lg | 24px | 3x |
| xl | 32px | 4x |
| 2xl | 48px | 6x |
| 3xl | 64px | 8x |

## Grid System

- **Columns**: 12
- **Column Width**: 60px
- **Gutter**: 24px (between columns)
- **Margin**: 48px (page margins)
- **Max Width**: 1440px
- **Baseline Grid**: 24px line height

### Usage

```html
<!-- 12-column grid -->
<div class="grid">
  <div class="col-6">Half width</div>
  <div class="col-6">Half width</div>
</div>

<!-- Or use container class -->
<div class="container">
  Content with proper margins
</div>
```

## Components

### Buttons

```html
<!-- Primary (filled) -->
<button class="btn btn-primary">Primary Action</button>

<!-- Secondary (outline) -->
<button class="btn btn-secondary">Secondary</button>

<!-- Tertiary (outline) -->
<button class="btn btn-tertiary">Tertiary</button>

<!-- Sizes -->
<button class="btn btn-small">Small</button>
<button class="btn btn-large">Large</button>
```

### Badges

```html
<!-- Default badge -->
<span class="badge">Label</span>

<!-- Color variants -->
<span class="badge badge-primary">Primary</span>
<span class="badge badge-secondary">Secondary</span>
<span class="badge badge-tertiary">Tertiary</span>
```

### Cards

```html
<div class="card">
  <h3 class="card-title">Card Title</h3>
  <p class="card-description">Card content goes here</p>
  <div class="card-footer">
    <a href="#">Link</a>
  </div>
</div>
```

### Tables

```html
<table>
  <thead>
    <tr>
      <th>Header 1</th>
      <th>Header 2</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Data 1</td>
      <td>Data 2</td>
    </tr>
  </tbody>
</table>
```

## Visual Hierarchy Levels

### L1 - Primary
- **Size**: Largest (4xl)
- **Weight**: Bold
- **Position**: Prominent
- **Use**: Product name, section titles
- **Class**: `.h-l1`

### L2 - Secondary
- **Size**: Medium (2xl)
- **Weight**: Medium
- **Position**: Supporting
- **Use**: Model identifier, subtitles
- **Class**: `.h-l2`

### L3 - Tertiary
- **Size**: Small (base)
- **Weight**: Regular
- **Position**: Informational
- **Use**: Descriptions, annotations
- **Class**: `.h-l3`

### L4 - Quaternary
- **Size**: Smallest (xs)
- **Weight**: Regular
- **Position**: Labels only
- **Use**: Labels, technical data
- **Class**: `.h-l4`

## Shadows & Elevation

Philosophy: **Minimal shadows, content creates depth**

| Level | Shadow | Use |
|-------|--------|-----|
| **Flat** | None | Primary content surfaces |
| **Subtle** | 0 2px 8px rgba(0,0,0,0.04) | Hover states |
| **Medium** | 0 4px 16px rgba(0,0,0,0.06) | Floating elements |
| **Large** | 0 8px 32px rgba(0,0,0,0.08) | Modals (rarely used) |

## Utility Classes

### Text Alignment
- `.text-left` - Align text left
- `.text-center` - Align text center
- `.text-right` - Align text right

### Text Color
- `.text-primary` - Primary text color
- `.text-secondary` - Secondary text color
- `.text-tertiary` - Tertiary text color
- `.text-accent-primary` - Orange accent
- `.text-accent-secondary` - Green accent
- `.text-accent-tertiary` - Blue accent

### Background
- `.bg-white` - White background
- `.bg-neutral` - Neutral background

### Font Weight
- `.font-regular` - 400 weight
- `.font-medium` - 500 weight
- `.font-semibold` - 600 weight
- `.font-bold` - 700 weight

### Spacing
- `.mt-*` - Margin top (xs, sm, md, lg, xl, 2xl, 3xl)
- `.mb-*` - Margin bottom (xs, sm, md, lg, xl, 2xl, 3xl)
- `.p-*` - Padding (xs, sm, md, lg, xl, 2xl, 3xl)

### Opacity
- `.opacity-50` - 50% opacity
- `.opacity-75` - 75% opacity

## Responsive Breakpoints

- **Desktop**: > 1024px (12-column grid)
- **Tablet**: 768px - 1024px (8-column grid)
- **Mobile**: < 768px (4-column grid)
- **Small Mobile**: < 480px (adjusted sizes)

## Information Architecture Flow

The website follows an emotional-to-rational progression:

1. **Hero** - Emotional connection through pure form
2. **Philosophy** - Intellectual justification
3. **Internal Structure** - Technical excellence demonstration
4. **Dimensions** - Precision communication
5. **Specifications** - Complete information
6. **App Interface** - Ecosystem visualization

## CSS Variable Reference

All design tokens are defined as CSS variables in `/css/variables.css`:

```css
/* Colors */
--color-neutral-cream
--color-neutral-white
--color-material-light/medium/dark
--color-accent-primary/secondary/tertiary

/* Typography */
--font-primary
--font-size-*
--font-weight-*

/* Spacing */
--space-xs through --space-3xl

/* Grid */
--grid-columns
--grid-column-width
--grid-gutter
--grid-margin

/* Shadows */
--shadow-subtle/medium/large

/* Transitions */
--transition-fast/standard/slow
```

## Best Practices

1. **Use CSS Variables**: Always reference design tokens, not hardcoded values
2. **Maintain Hierarchy**: Follow visual hierarchy levels for consistent prominence
3. **Space Consistently**: Use spacing scale multiples of 8px
4. **Color Sparingly**: Use accent colors only for functional/interactive elements
5. **Responsive First**: Design for mobile first, enhance for larger screens
6. **Typography Scale**: Stick to the modular scale for consistent proportions
7. **Shadows Minimally**: Use shadows sparingly to maintain simplicity

## File Structure

```
/css/
  ├── variables.css        # CSS variables and design tokens
  └── design-system.css    # Component styles and utilities

/_layouts/
  └── default.html         # Jekyll layout for all markdown pages

/index.html               # Landing page
/_config.yml              # Jekyll configuration
```

## Customization

To customize the design system:

1. **Colors**: Edit color variables in `/css/variables.css`
2. **Typography**: Adjust font sizes and weights in variables
3. **Spacing**: Modify base unit (currently 8px) in variables
4. **Components**: Update component styles in `/css/design-system.css`

## Browser Support

- Modern browsers (Chrome, Firefox, Safari, Edge)
- CSS Grid support required
- CSS Custom Properties (CSS Variables) required

---

**Last Updated**: 2026-05-24
**Version**: 1.0
**Based on**: Dieter Rams' Design Principles + Swiss Style Systems
