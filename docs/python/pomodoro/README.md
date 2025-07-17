# ⏳ Terminal-based Pomodoro Timer

A simple **command-line Pomodoro Timer** written in Python for improving productivity using the **Pomodoro Technique**. It alternates between work and break sessions, helps reduce burnout, and includes a terminal beep as a reminder.

---

## 📚 What is the Pomodoro Technique?

The Pomodoro Technique is a **time management method** developed by Francesco Cirillo. It uses a timer to break work into intervals, traditionally:
- **25 minutes** of focused work  
- **5 minutes** of break  
- Repeated for 4 cycles

This timer helps maintain focus and avoid mental fatigue.

---

## 🧠 What This Project Does

- Runs in your terminal/command prompt
- Displays countdown timers for work and break sessions
- Beeps (`\a`) when a session ends
- Loops through 4 full Pomodoro cycles (Work → Break)

---

## 🚀 How to Run

### ✅ Requirements
- Python 3 installed

### ▶️ Run the Program
```bash
python pomodoro_timer.py
```

---

## 🧾 Code Explanation

Here’s a breakdown of all logic, functions, and methods used in the code.

---

### ✅ `import time`

This imports Python's built-in `time` module.

Used for:
- **`time.sleep(seconds)`**: Pauses the program for the given number of seconds to create a countdown effect.

---

### ✅ `def format_time(seconds)`

**Purpose**: Converts total seconds to a readable format like `mm:ss`.

```python
def format_time(seconds):
    mins = seconds // 60       # Integer division to get minutes
    secs = seconds % 60        # Remainder to get seconds
    return f"{mins:02d}:{secs:02d}"  # Format with leading zeroes
```

Example:
- `format_time(150)` returns `'02:30'`

---

### ✅ Timer Settings

```python
work_duration = 25 * 60     # 25 minutes in seconds
break_duration = 5 * 60     # 5 minutes in seconds
cycles = 4                  # Total work+break cycles
```

You can change these values for quick testing (e.g. `work_duration = 5` for 5 seconds).

---

### ✅ The Loop: Running Pomodoro Cycles

```python
for cycle in range(1, cycles + 1):
```
- Runs the work + break cycle **4 times**
- `range(1, cycles + 1)` includes values 1 to 4

---

### ✅ Work Timer

```python
for remaining in range(work_duration, 0, -1):
    print(f"Work: {format_time(remaining)}", end='\r')
    time.sleep(1)
```

**What it does**:
- Counts down from 1500 seconds (25 mins)
- Updates the timer every second
- `end='\r'` makes it overwrite the same line in terminal
- `\a` prints a beep sound in terminal

---

### ✅ Break Timer

Same logic as the work timer but with `break_duration` (300 seconds / 5 mins).

---

### ✅ Terminal Beep

```python
print("\a")
```

This plays the **bell sound** in the terminal (depends on your system’s sound settings). It alerts you when the work or break time is over.

---

## 🎨 Output Example

```
Pomodoro Cycle 1 - Work Time!
Work: 24:59
Work: 24:58
...

Time to take a break! 🔔
Break: 04:59
Break: 04:58
...

Break over! Get ready to work again.
```

---

## ✅ Features Summary

| Feature              | Description                          |
|----------------------|--------------------------------------|
| Timer Format         | Countdown in mm:ss format            |
| Terminal Refresh     | Clean, single-line updates           |
| Sound Alert          | Terminal bell `\a` at session end    |
| Cycles               | 4 repeated Pomodoro sessions         |
| Beginner-Friendly    | No external libraries, pure Python   |

---

## 🧪 Customize It!

| What you want to change | How to do it                        |
|--------------------------|-------------------------------------|
| Shorter testing times    | Change `work_duration` and `break_duration` to smaller numbers |
| More Pomodoros           | Change the value of `cycles`        |
| Add long breaks          | Add a longer break after 4 cycles   |
| Add GUI or sound files   | Use libraries like `tkinter` or `playsound` |

---

## 🙌 Contribution Ideas

- Add a longer break after 4 cycles
- Create a GUI with Tkinter or PyQt
- Log session stats to a text file
- Use `playsound` for custom audio alerts

---

## 📃 License

This project is open-source and free to use for personal productivity!
