# Password Strength Checker

A command-line tool that evaluates the strength of a password using real security metrics rather than simple rule-counting. Instead of only asking "does it have an uppercase letter?", it measures how genuinely hard a password is to guess and explains *why* a password is weak or strong.

This project is built primarily as a learning and portfolio project with a focus on clean, extensible design (SOLID principles) and security-oriented thinking.

## Motivation

Most beginner password checkers just count character types and call a password "strong" if it ticks enough boxes. That approach is misleading — `Password1!` passes every box but is trivially guessable. This tool aims to evaluate a password the way an attacker would actually think about it: how large is the search space, is it a known common password, and does it contain predictable patterns?

## Features

### Entropy-based scoring
The core metric. The tool estimates the password's entropy in bits based on the size of the character pool it draws from and its length. This converts "strength" from a subjective checklist into a measurable number, giving a far more honest picture of how guessable a password really is.

### Common password blacklist
The password is checked against a list of known weak/common passwords (a subset of widely leaked password lists). If a password appears on the list, it is flagged as critically weak regardless of its other properties — because a high-entropy-looking password that is already in every attacker's dictionary offers no real protection.

### Pattern detection
Detects predictable structures that inflate apparent strength without adding real security: sequential characters (`1234`, `abcd`), keyboard walks (`qwerty`, `asdf`), and repeated characters (`aaaa`). These reduce the final score even when raw entropy looks high.

### Estimated crack time
Translates the entropy value into a human-readable estimate of how long a password would take to crack under a given guessing rate. Telling a user "this would take 3 seconds" versus "this would take 300 years" is far more meaningful than an abstract score.

### Actionable feedback
Rather than only returning a verdict, the tool explains each weakness and gives concrete suggestions (e.g. "this password appears in a common-password list", "sequential pattern `1234` detected", "increase length to 12+ characters").

## Design

The evaluation logic is built around independent, composable rules rather than one large function. Each rule examines the password from one angle and reports its own contribution, making it easy to add new rules without touching existing ones (Open/Closed Principle). An orchestrator runs all the rules, computes the entropy and crack-time metrics, applies override logic (e.g. a blacklisted password stays "weak" no matter what), and packages everything into a single result object that separates the *calculation* from the *reported outcome*.

## Status

🚧 In development — design phase. UML class and sequence diagrams are being drawn before implementation.

## Roadmap

- [ ] UML class & sequence diagrams
- [ ] Core evaluation engine (length + character variety)
- [ ] Entropy calculation
- [ ] Common-password blacklist
- [ ] Pattern detection
- [ ] Crack-time estimation
- [ ] CLI interface
- [ ] Tests

## Tech

Java (initial implementation). A Python port is planned as a follow-up.
