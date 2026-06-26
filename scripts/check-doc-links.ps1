$ErrorActionPreference = 'Stop'

$docsRoot = (Resolve-Path -LiteralPath 'docs').Path
$missing = @()
$files = Get-ChildItem -Path $docsRoot -Recurse -File -Filter *.md |
  Where-Object { $_.FullName -notlike (Join-Path $docsRoot 'superpowers\*') }
$pattern = '(!?\[[^\]]*\]\()((?!https?://|mailto:|#)[^)]+)(\))'

foreach ($file in $files) {
  $content = [System.IO.File]::ReadAllText($file.FullName, [System.Text.Encoding]::UTF8)
  $matches = [regex]::Matches($content, $pattern)

  foreach ($match in $matches) {
    $rawTarget = $match.Groups[2].Value
    $target = ($rawTarget -split '#')[0]
    if ([string]::IsNullOrWhiteSpace($target)) {
      continue
    }

    $decoded = [uri]::UnescapeDataString($target)
    $candidate = Join-Path $file.DirectoryName $decoded

    $candidateExists =
      (Test-Path -LiteralPath $candidate) -or
      (Test-Path -LiteralPath "$candidate.md") -or
      (Test-Path -LiteralPath (Join-Path $candidate 'index.md'))

    if (-not $candidateExists) {
      $relativeFile = Resolve-Path -LiteralPath $file.FullName -Relative
      $missing += "$relativeFile`: $rawTarget"
    }
  }
}

if ($missing.Count -gt 0) {
  $missing | ForEach-Object { Write-Output $_ }
  exit 1
}

Write-Output 'All local Markdown links resolve.'
