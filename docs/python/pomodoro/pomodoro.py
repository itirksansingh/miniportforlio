import time

# Function to format time in mm:ss
def format_time(seconds):
    mins = seconds // 60
    secs = seconds % 60
    return f"{mins:02d}:{secs:02d}"

# Pomodoro settings
work_duration = 25 * 60  # 25 minutes
break_duration = 5 * 60  # 5 minutes
cycles = 4

for cycle in range(1, cycles + 1):
    print(f"\nPomodoro Cycle {cycle} - Work Time!")
    
    # Work session
    for remaining in range(work_duration, 0, -1):
        print(f"Work: {format_time(remaining)}", end='\r')
        time.sleep(1)
    
    print("\nTime to take a break! \a")  # Terminal beep

    # Break session
    for remaining in range(break_duration, 0, -1):
        print(f"Break: {format_time(remaining)}", end='\r')
        time.sleep(1)
    
    print("\nBreak over! Get ready to work again. \a")

print("\n🎉 All Pomodoro cycles complete! Well done! \a")
