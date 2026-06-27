$ErrorActionPreference = 'Stop'

$utf8NoBom = New-Object System.Text.UTF8Encoding($false)
$docsRoot = (Resolve-Path -LiteralPath 'docs').Path

$files = Get-ChildItem -Path $docsRoot -Recurse -File -Filter *.md |
  Where-Object { $_.FullName -notlike (Join-Path $docsRoot 'superpowers\*') }

foreach ($file in $files) {
  $content = [System.IO.File]::ReadAllText($file.FullName, [System.Text.Encoding]::UTF8)
  $original = $content

  if ($content -match '(?s)^---\s*\r?\n(.*?)\r?\n---\s*\r?\n') {
    $frontMatterBlock = $Matches[0]
    $frontMatter = $Matches[1]
    $body = $content.Substring($frontMatterBlock.Length)
    $titleLine = ($frontMatter -split "\r?\n" | Where-Object { $_ -match '^title:\s*(.+)$' } | Select-Object -First 1)

    if ($titleLine) {
      $content = "---`n$titleLine`n---`n`n$body"
    } else {
      $content = $body
    }
  }

  $content = $content -replace '\]\(/README\)', '](../README.md)'
  $content = $content -replace '\]\(/OS/', '](../os/'
  $content = $content -replace '\]\(/Computer%20Architecture/', '](../computer-architecture/'
  $content = $content -replace '\]\(/data-structure-and-algorithm/', '](../ds-algorithm/'
  $content = $content -replace '\]\(/OOAD/', '](../ooad/'

  if ($content -ne $original) {
    [System.IO.File]::WriteAllText($file.FullName, $content, $utf8NoBom)
  }
}
